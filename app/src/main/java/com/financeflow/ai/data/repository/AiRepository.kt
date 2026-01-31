package com.financeflow.ai.data.repository

import android.content.Context
import android.net.Uri
import com.financeflow.ai.domain.model.Transaction
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor

class AiRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }
    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun analyze(
        uri: Uri, 
        apiKey: String, 
        provider: String, 
        baseUrl: String, 
        modelName: String,
        customPrompt: String? = null
    ): List<Transaction> {
        return when (provider) {
            "Gemini" -> analyzeWithGemini(uri, apiKey, modelName, customPrompt)
            else -> analyzeWithOpenAi(uri, apiKey, baseUrl, modelName, customPrompt)
        }
    }

    suspend fun getAdvice(
        transactions: List<Transaction>, 
        apiKey: String, 
        provider: String, 
        baseUrl: String, 
        modelName: String,
        customPrompt: String? = null
    ): String {
        val transactionSummary = transactions.take(20).joinToString("\n") { "${it.merchant}: ${it.amount} (${it.category})" }
        val defaultAdvicePrompt = """
            Sen mert, açık sözlü ve bilgili bir finans koçusun. 
            Şu son işlemleri incele ve 1-2 cümlelik vuran, faydalı bir tavsiye ver. 
            Harcamalar ₺ (Türk Lirası) cinsindendir.
            Motivasyonel ama gerçekçi ol. Emojiler kullan. Türkçe yanıt ver.
        """.trimIndent()

        val prompt = (if (customPrompt.isNullOrBlank()) defaultAdvicePrompt else customPrompt) + "\n\nİşlemler:\n$transactionSummary"
        
        return queryAiText(prompt, apiKey, provider, baseUrl, modelName)
    }

    suspend fun predictCategory(merchant: String, apiKey: String, provider: String, baseUrl: String, modelName: String): String {
        val prompt = "Şu satıcı için en uygun kategoriyi (Grocery, Tech, Entertainment, Food, Salary, Other) tahmin et: $merchant. SADECE kategori adını döndür (İngilizce olarak: Grocery, Tech, Entertainment, Food, Salary veya Other)."
        return queryAiText(prompt, apiKey, provider, baseUrl, modelName).trim()
    }

    private suspend fun queryAiText(prompt: String, apiKey: String, provider: String, baseUrl: String, modelName: String): String {
        val fallback = "Bugünlük tavsiye yok, çalışmaya devam! 🚀"
        return try {
            if (provider == "Gemini") {
                val model = GenerativeModel(modelName = modelName, apiKey = apiKey)
                model.generateContent(prompt).text ?: fallback
            } else {
                val response = httpClient.post(baseUrl + "chat/completions") {
                    header(HttpHeaders.Authorization, "Bearer $apiKey")
                    contentType(ContentType.Application.Json)
                    setBody(OpenAiRequest(
                        model = modelName,
                        messages = listOf(Message("user", prompt))
                    ))
                }
                if (response.status.value in 200..299) {
                    val openAiResponse: OpenAiResponse = response.body()
                    openAiResponse.choices.firstOrNull()?.message?.content ?: fallback
                } else {
                    val errorBody = try { response.bodyAsText() } catch(e: Exception) { "" }
                    "AI hatası (${response.status.value}): $errorBody"
                }
            }
        } catch (e: Exception) {
            "Bağlantı hatası: ${e.localizedMessage}"
        }
    }

    private suspend fun analyzeWithGemini(uri: Uri, apiKey: String, modelName: String, customPrompt: String? = null): List<Transaction> {
        val model = GenerativeModel(
            modelName = modelName,
            apiKey = apiKey,
            generationConfig = generationConfig { responseMimeType = "application/json" }
        )
        val pdfBytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
            ?: throw Exception("Empty PDF")

        val finalPrompt = if (customPrompt.isNullOrBlank()) getPrompt() else customPrompt

        val response = model.generateContent(
            content {
                blob("application/pdf", pdfBytes)
                text(finalPrompt)
            }
        )
        val text = response.text ?: throw Exception("Gemini returned nothing")
        return parseJson(text)
    }

    private suspend fun analyzeWithOpenAi(uri: Uri, apiKey: String, baseUrl: String, modelName: String, customPrompt: String? = null): List<Transaction> {
        val pdfText = extractTextFromPdf(uri)
        var finalPrompt = if (customPrompt.isNullOrBlank()) getPrompt() else customPrompt
        
        // OpenAI json_object format REQUIRES the word 'json' in the prompt
        if (!finalPrompt.lowercase().contains("json")) {
            finalPrompt += "\n\nImportant: Return the result as a JSON array."
        }
        
        val promptWithContext = finalPrompt + "\n\nHere is the statement text:\n$pdfText"

        val response = httpClient.post(baseUrl + "chat/completions") {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(OpenAiRequest(
                model = modelName, 
                messages = listOf(Message("user", promptWithContext)),
                response_format = ResponseFormat("json_object")
            ))
        }

        if (response.status.value in 200..299) {
            val openAiResponse: OpenAiResponse = response.body()
            val content = openAiResponse.choices.firstOrNull()?.message?.content 
                ?: throw Exception("OpenAI returned empty choices")
            return parseJson(content)
        } else {
            val errorBody = try { response.bodyAsText() } catch (e: Exception) { "Unknown error" }
            throw Exception("AI hatası (${response.status.value}): $errorBody")
        }
    }

    private fun extractTextFromPdf(uri: Uri): String {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                val reader = PdfReader(inputStream)
                val totalPages = reader.numberOfPages
                val textBuilder = StringBuilder()
                for (i in 1..totalPages) {
                    textBuilder.append(PdfTextExtractor.getTextFromPage(reader, i))
                }
                reader.close()
                textBuilder.toString()
            } ?: ""
        } catch (e: Exception) {
            "Error extracting text: ${e.message}"
        }
    }

    private fun getPrompt() = """
        Extract transactions as JSON array.
        Each object: date (Unix ms), merchant (String), amount (Double, exp positive, inc negative, currency is ₺), category (Grocery, Tech, Entertainment, Food, Salary, Other).
        Return ONLY valid JSON array.
    """.trimIndent()

    private fun parseJson(text: String): List<Transaction> {
        val cleanJson = if (text.contains("[")) text.substring(text.indexOf("["), text.lastIndexOf("]") + 1) else text
        val aiTransactions = try {
            json.decodeFromString<List<AITransaction>>(cleanJson)
        } catch (e: Exception) {
            // Some models return wrapped in an object
             json.decodeFromString<OpenAiCompatibleWrapper>(cleanJson).transactions
        }
        return aiTransactions.map { it.toDomainTransaction() }
    }
}

@Serializable
data class OpenAiCompatibleWrapper(val transactions: List<AITransaction>)

@Serializable
data class AITransaction(
    val date: Long,
    val merchant: String,
    val amount: Double,
    val category: String,
    val description: String? = null
) {
    fun toDomainTransaction() = Transaction(
        date = date,
        merchant = merchant,
        amount = amount,
        category = category,
        description = description,
        isAIGenerated = true
    )
}

@Serializable
data class OpenAiRequest(
    val model: String,
    val messages: List<Message>,
    val response_format: ResponseFormat? = null
)

@Serializable
data class Message(val role: String, val content: String)

@Serializable
data class ResponseFormat(val type: String)

@Serializable
data class OpenAiResponse(val choices: List<Choice>)

@Serializable
data class Choice(val message: Message)

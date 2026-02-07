package com.financeflow.ai.ui.dashboard

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.financeflow.ai.data.local.PreferenceManager
import com.financeflow.ai.data.local.TransactionDao
import com.financeflow.ai.data.repository.AiRepository
import com.financeflow.ai.domain.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.financeflow.ai.R
import javax.inject.Inject

sealed class DashboardState {
    object Idle : DashboardState()
    object Analyzing : DashboardState()
    object MissingApiKey : DashboardState()
    data class Success(val messageResId: Int) : DashboardState()
    data class Error(val errorResId: Int, val dynamicValue: String? = null) : DashboardState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val transactionDao: TransactionDao,
    private val aiRepository: AiRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardState>(DashboardState.Idle)
    val uiState: StateFlow<DashboardState> = _uiState
    
    private val _advice = MutableStateFlow<String?>(null)
    val advice: StateFlow<String?> = _advice

    private val _isPredictingCategory = MutableStateFlow(false)
    val isPredictingCategory: StateFlow<Boolean> = _isPredictingCategory

    val apiKey = preferenceManager.apiKey.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val provider = preferenceManager.provider.stateIn(viewModelScope, SharingStarted.Eagerly, "Gemini")
    val baseUrl = preferenceManager.baseUrl.stateIn(viewModelScope, SharingStarted.Eagerly, "https://api.openai.com/v1/")
    val modelName = preferenceManager.modelName.stateIn(viewModelScope, SharingStarted.Eagerly, "gemini-2.0-flash")
    val advicePrompt = preferenceManager.advicePrompt.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val analysisPrompt = preferenceManager.analysisPrompt.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val transactions = transactionDao.getAllTransactions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val netBalance = transactions.map { list ->
        // Manual salaries (isAIGenerated = false and negative amount)
        val salary = list
            .filter { !it.isAIGenerated && it.amount < 0 }
            .sumOf { kotlin.math.abs(it.amount) }
            
        // Spending: All positive amounts EXCEPT those categorized as 'Payment'
        val spending = list
            .filter { it.amount > 0 && it.category != "Payment" }
            .sumOf { it.amount }
            
        salary - spending
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val monthlyDebt = transactions.map { list ->
        list.filter { it.amount > 0 && it.category != "Payment" }.sumOf { it.amount }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val monthlyIncome = transactions.map { list ->
        list.filter { !it.isAIGenerated && it.amount < 0 }.sumOf { kotlin.math.abs(it.amount) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    init {
        // Automatically refresh advice when transactions change
        viewModelScope.launch {
            transactions.collectLatest { list ->
                if (list.isNotEmpty()) {
                    refreshAdvice(list)
                }
            }
        }
    }

    private fun refreshAdvice(currentTransactions: List<Transaction>) {
        val key = apiKey.value ?: return
        viewModelScope.launch {
            try {
                val newAdvice = aiRepository.getAdvice(
                    currentTransactions,
                    key,
                    provider.value,
                    baseUrl.value,
                    modelName.value,
                    advicePrompt.value
                )
                _advice.value = newAdvice
            } catch (e: Exception) {
                // Silently fail for advice
            }
        }
    }

    suspend fun predictCategory(merchant: String): String? {
        val key = apiKey.value ?: return null
        _isPredictingCategory.value = true
        return try {
            val category = aiRepository.predictCategory(
                merchant,
                key,
                provider.value,
                baseUrl.value,
                modelName.value
            )
            category
        } catch (e: Exception) {
            null
        } finally {
            _isPredictingCategory.value = false
        }
    }

    val spendingByCategory = transactionDao.getSpendingByCategory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveSettings(key: String, provider: String, url: String, model: String, advice: String, analysis: String) {
        viewModelScope.launch {
            preferenceManager.saveSettings(key, provider, url, model, advice, analysis)
        }
    }

    val monthlyGroupedTransactions: StateFlow<Map<String, List<Transaction>>> = transactions
        .map { list ->
            list.groupBy { transaction ->
                val formatter = java.text.SimpleDateFormat("MMMM yyyy", java.util.Locale.getDefault())
                formatter.format(java.util.Date(transaction.date))
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    fun addManualTransaction(merchant: String, amount: Double, category: String, isIncome: Boolean, pdfUri: Uri?) {
        viewModelScope.launch {
            val transaction = Transaction(
                date = System.currentTimeMillis(),
                merchant = merchant,
                amount = if (isIncome) -amount else amount, // Negative for income
                category = category,
                description = if (isIncome) "Income" else "Manual Expense",
                isAIGenerated = false,
                pdfUri = pdfUri?.toString()
            )
            transactionDao.insertTransactions(listOf(transaction))
        }
    }

    fun analyzePDFs(uris: List<Uri>) {
        val currentKey = apiKey.value
        if (currentKey.isNullOrBlank()) {
            _uiState.value = DashboardState.MissingApiKey
            return
        }

        viewModelScope.launch {
            _uiState.value = DashboardState.Analyzing
            try {
                uris.forEach { uri ->
                    val analyzedTransactions = aiRepository.analyze(
                        uri, 
                        currentKey, 
                        provider.value, 
                        baseUrl.value,
                        modelName.value,
                        analysisPrompt.value
                    )
                    transactionDao.insertTransactions(analyzedTransactions)
                }
                _uiState.value = DashboardState.Success(R.string.analysis_success)
            } catch (e: Exception) {
                _uiState.value = DashboardState.Error(R.string.analysis_error, e.message)
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.deleteTransaction(transaction)
        }
    }
}

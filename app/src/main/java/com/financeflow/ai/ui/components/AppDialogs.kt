package com.financeflow.ai.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financeflow.ai.ui.theme.Indigo600
import androidx.compose.ui.res.stringResource
import com.financeflow.ai.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiKeyDialog(
    currentKey: String,
    currentProvider: String,
    currentBaseUrl: String,
    currentModel: String,
    currentAdvicePrompt: String,
    currentAnalysisPrompt: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String, String, String, String) -> Unit
) {
    var text by remember { mutableStateOf(currentKey) }
    var provider by remember { mutableStateOf(currentProvider) }
    var baseUrl by remember { mutableStateOf(currentBaseUrl) }
    var modelName by remember { mutableStateOf(currentModel) }
    var advicePrompt by remember { mutableStateOf(currentAdvicePrompt) }
    var analysisPrompt by remember { mutableStateOf(currentAnalysisPrompt) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { 
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.command_center), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(stringResource(R.string.select_provider), style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                
                // Provider Selection
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = provider == "Gemini", 
                        onClick = { 
                            provider = "Gemini"
                            modelName = "gemini-2.0-flash"
                        }, 
                        label = { Text("Gemini") }
                    )
                    FilterChip(
                        selected = provider == "OpenAI", 
                        onClick = { 
                            provider = "OpenAI"
                            baseUrl = "https://api.openai.com/v1/"
                            modelName = "gpt-4o"
                        }, 
                        label = { Text("OpenAI") }
                    )
                }

                // Quick Presets for OpenAI-Compatible providers
                if (provider == "OpenAI") {
                    Text(stringResource(R.string.quick_presets), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SuggestionChip(
                            onClick = { 
                                baseUrl = "https://api.groq.com/openai/v1/"
                                modelName = "llama-3.3-70b-versatile"
                            },
                            label = { Text("Groq ⚡") }
                        )
                        SuggestionChip(
                            onClick = { 
                                baseUrl = "https://openrouter.ai/api/v1/"
                                modelName = "google/gemini-2.0-flash-exp:free"
                            },
                            label = { Text("OpenRouter 🌍") }
                        )
                        SuggestionChip(
                            onClick = { 
                                baseUrl = "https://api.mistral.ai/v1/"
                                modelName = "mistral-tiny"
                            },
                            label = { Text("Mistral 🇫🇷") }
                        )
                    }
                }
                
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(stringResource(R.string.api_key)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                // Model Selection Dropdown
                val geminiModels = listOf("gemini-2.0-flash", "gemini-2.0-flash-exp", "gemini-1.5-pro", "gemini-1.5-flash")
                val openaiModels = listOf("gpt-4o", "gpt-4o-mini", "llama-3.3-70b-versatile", "google/gemini-2.0-flash-exp:free")
                val availableModels = if (provider == "Gemini") geminiModels else openaiModels
                
                var modelExpanded by remember { mutableStateOf(false) }
                var isCustomModel by remember(provider) { mutableStateOf(modelName !in availableModels) }

                Text(stringResource(R.string.select_model), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Box {
                    OutlinedButton(
                        onClick = { modelExpanded = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(if (isCustomModel) "Custom: $modelName" else modelName)
                    }
                    DropdownMenu(expanded = modelExpanded, onDismissRequest = { modelExpanded = false }) {
                        availableModels.forEach { m ->
                            DropdownMenuItem(
                                text = { Text(m) },
                                onClick = { 
                                    modelName = m
                                    isCustomModel = false
                                    modelExpanded = false 
                                }
                            )
                        }
                        DropdownMenuItem(
                            text = { Text("Other (Type manual)...") },
                            onClick = { 
                                isCustomModel = true
                                modelExpanded = false 
                            }
                        )
                    }
                }

                if (isCustomModel) {
                    OutlinedTextField(
                        value = modelName,
                        onValueChange = { modelName = it },
                        label = { Text(stringResource(R.string.model_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                if (provider == "OpenAI") {
                    OutlinedTextField(
                        value = baseUrl,
                        onValueChange = { baseUrl = it },
                        label = { Text(stringResource(R.string.base_url)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                Divider(color = Color.White.copy(alpha = 0.1f))

                OutlinedTextField(
                    value = advicePrompt,
                    onValueChange = { advicePrompt = it },
                    label = { Text(stringResource(R.string.custom_advice_prompt)) },
                    placeholder = { Text(stringResource(R.string.default_advice_prompt)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 3
                )

                OutlinedTextField(
                    value = analysisPrompt,
                    onValueChange = { analysisPrompt = it },
                    label = { Text(stringResource(R.string.custom_analysis_prompt)) },
                    placeholder = { Text(stringResource(R.string.default_analysis_prompt)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(text, provider, baseUrl, modelName, advicePrompt, analysisPrompt); onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Indigo600),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.update_settings), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.cancel), color = Color.Gray)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualEntryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Double, String, Boolean) -> Unit,
    predictCategory: suspend (String) -> String?,
    isPredicting: Boolean
) {
    var merchant by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Grocery") }
    var isIncome by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isIncome) stringResource(R.string.bag_secured) else stringResource(R.string.new_burn)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                     FilterChip(
                        selected = !isIncome,
                        onClick = { isIncome = false },
                        label = { Text(stringResource(R.string.expense)) }
                    )
                    FilterChip(
                        selected = isIncome,
                        onClick = { isIncome = true },
                        label = { Text(stringResource(R.string.income)) }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = merchant,
                        onValueChange = { merchant = it },
                        label = { Text(stringResource(R.string.merchant_name)) },
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { 
                            scope.launch {
                                val predicted = predictCategory(merchant)
                                if (predicted != null) category = predicted
                            }
                        },
                        enabled = merchant.isNotBlank() && !isPredicting
                    ) {
                        if (isPredicting) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                        } else {
                            Text("🪄", fontSize = 20.sp)
                        }
                    }
                }
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text(stringResource(R.string.amount)) },
                    modifier = Modifier.fillMaxWidth()
                )
                val categories = listOf("Grocery", "Tech", "Entertainment", "Food", "Salary", "Bonus", "Other")
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                        Text("${stringResource(R.string.category)}: $category")
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = { category = cat; expanded = false }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(merchant, amount.toDoubleOrNull() ?: 0.0, category, isIncome)
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Indigo600)
            ) {
                Text(stringResource(R.string.add_entry))
            }
        }
    )
}

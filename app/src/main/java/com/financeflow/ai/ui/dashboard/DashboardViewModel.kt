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

    val transactions = transactionDao.getAllTransactions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalSpending = transactionDao.getTotalSpendingSince(0)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

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
                    modelName.value
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

    fun saveSettings(key: String, provider: String, url: String, model: String) {
        viewModelScope.launch {
            preferenceManager.saveSettings(key, provider, url, model)
        }
    }

    fun addManualTransaction(merchant: String, amount: Double, category: String, isIncome: Boolean) {
        viewModelScope.launch {
            val transaction = Transaction(
                date = System.currentTimeMillis(),
                merchant = merchant,
                amount = if (isIncome) -amount else amount, // Negative for income
                category = category,
                description = if (isIncome) "Income" else "Manual Expense",
                isAIGenerated = false
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
                        modelName.value
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

package com.financeflow.ai.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ0\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020 2\u0006\u00108\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\r2\b\u0010:\u001a\u0004\u0018\u00010;J\u0014\u0010<\u001a\u0002052\f\u0010=\u001a\b\u0012\u0004\u0012\u00020;0$J\u000e\u0010>\u001a\u0002052\u0006\u0010?\u001a\u00020%J\u001b\u0010@\u001a\u0004\u0018\u00010\u000b2\u0006\u00106\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ\u0016\u0010B\u001a\u0002052\f\u0010C\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002J6\u0010D\u001a\u0002052\u0006\u0010E\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010F\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020\u000bR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013R)\u0010\"\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0013R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020 0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0013R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020 0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0013R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0$0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0013R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006I"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "transactionDao", "Lcom/financeflow/ai/data/local/TransactionDao;", "aiRepository", "Lcom/financeflow/ai/data/repository/AiRepository;", "preferenceManager", "Lcom/financeflow/ai/data/local/PreferenceManager;", "(Lcom/financeflow/ai/data/local/TransactionDao;Lcom/financeflow/ai/data/repository/AiRepository;Lcom/financeflow/ai/data/local/PreferenceManager;)V", "_advice", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isPredictingCategory", "", "_uiState", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "advice", "Lkotlinx/coroutines/flow/StateFlow;", "getAdvice", "()Lkotlinx/coroutines/flow/StateFlow;", "advicePrompt", "getAdvicePrompt", "analysisPrompt", "getAnalysisPrompt", "apiKey", "getApiKey", "baseUrl", "getBaseUrl", "isPredictingCategory", "modelName", "getModelName", "monthlyDebt", "", "getMonthlyDebt", "monthlyGroupedTransactions", "", "", "Lcom/financeflow/ai/domain/model/Transaction;", "getMonthlyGroupedTransactions", "monthlyIncome", "getMonthlyIncome", "netBalance", "getNetBalance", "provider", "getProvider", "spendingByCategory", "Lcom/financeflow/ai/data/local/CategorySpending;", "getSpendingByCategory", "transactions", "getTransactions", "uiState", "getUiState", "addManualTransaction", "", "merchant", "amount", "category", "isIncome", "pdfUri", "Landroid/net/Uri;", "analyzePDFs", "uris", "deleteTransaction", "transaction", "predictCategory", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshAdvice", "currentTransactions", "saveSettings", "key", "url", "model", "analysis", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.financeflow.ai.data.local.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.financeflow.ai.data.repository.AiRepository aiRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.financeflow.ai.data.local.PreferenceManager preferenceManager = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.financeflow.ai.ui.dashboard.DashboardState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.financeflow.ai.ui.dashboard.DashboardState> uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _advice = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> advice = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPredictingCategory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPredictingCategory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> apiKey = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> provider = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> baseUrl = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> modelName = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> advicePrompt = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> analysisPrompt = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.financeflow.ai.domain.model.Transaction>> transactions = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> netBalance = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> monthlyDebt = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> monthlyIncome = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.financeflow.ai.data.local.CategorySpending>> spendingByCategory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.util.List<com.financeflow.ai.domain.model.Transaction>>> monthlyGroupedTransactions = null;
    
    @javax.inject.Inject
    public DashboardViewModel(@org.jetbrains.annotations.NotNull
    com.financeflow.ai.data.local.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull
    com.financeflow.ai.data.repository.AiRepository aiRepository, @org.jetbrains.annotations.NotNull
    com.financeflow.ai.data.local.PreferenceManager preferenceManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.financeflow.ai.ui.dashboard.DashboardState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getAdvice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPredictingCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getApiKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getModelName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getAdvicePrompt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getAnalysisPrompt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.financeflow.ai.domain.model.Transaction>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getNetBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getMonthlyDebt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getMonthlyIncome() {
        return null;
    }
    
    private final void refreshAdvice(java.util.List<com.financeflow.ai.domain.model.Transaction> currentTransactions) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object predictCategory(@org.jetbrains.annotations.NotNull
    java.lang.String merchant, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.financeflow.ai.data.local.CategorySpending>> getSpendingByCategory() {
        return null;
    }
    
    public final void saveSettings(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String provider, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    java.lang.String model, @org.jetbrains.annotations.NotNull
    java.lang.String advice, @org.jetbrains.annotations.NotNull
    java.lang.String analysis) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.util.List<com.financeflow.ai.domain.model.Transaction>>> getMonthlyGroupedTransactions() {
        return null;
    }
    
    public final void addManualTransaction(@org.jetbrains.annotations.NotNull
    java.lang.String merchant, double amount, @org.jetbrains.annotations.NotNull
    java.lang.String category, boolean isIncome, @org.jetbrains.annotations.Nullable
    android.net.Uri pdfUri) {
    }
    
    public final void analyzePDFs(@org.jetbrains.annotations.NotNull
    java.util.List<? extends android.net.Uri> uris) {
    }
    
    public final void deleteTransaction(@org.jetbrains.annotations.NotNull
    com.financeflow.ai.domain.model.Transaction transaction) {
    }
}
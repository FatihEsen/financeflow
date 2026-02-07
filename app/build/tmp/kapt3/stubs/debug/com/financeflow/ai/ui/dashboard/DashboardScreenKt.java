package com.financeflow.ai.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u0012\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0007\u001a%\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010\u00a2\u0006\u0002\b\u0011H\u0007\u001a\b\u0010\u0012\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\tH\u0007\u001a\u0010\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\u00a8\u0006\u0018"}, d2 = {"AdviceSection", "", "advice", "", "BarChart", "modifier", "Landroidx/compose/ui/Modifier;", "BentoGridSection", "income", "", "debt", "DashboardScreen", "viewModel", "Lcom/financeflow/ai/ui/dashboard/DashboardViewModel;", "GlassCard", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "HeaderSection", "SpendingOverview", "amount", "TransactionItem", "transaction", "Lcom/financeflow/ai/domain/model/Transaction;", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull
    com.financeflow.ai.ui.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void HeaderSection() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AdviceSection(@org.jetbrains.annotations.Nullable
    java.lang.String advice) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SpendingOverview(double amount) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void BentoGridSection(double income, double debt) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void GlassCard(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void BarChart(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TransactionItem(@org.jetbrains.annotations.NotNull
    com.financeflow.ai.domain.model.Transaction transaction) {
    }
}
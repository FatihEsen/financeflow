package com.financeflow.ai.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001ax\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n20\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001ar\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2$\u0010\u000e\u001a \u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u000f2$\u0010\u0012\u001a \b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"ApiKeyDialog", "", "currentKey", "", "currentProvider", "currentBaseUrl", "currentModel", "currentAdvicePrompt", "currentAnalysisPrompt", "onDismiss", "Lkotlin/Function0;", "onSave", "Lkotlin/Function6;", "ManualEntryDialog", "onConfirm", "Lkotlin/Function4;", "", "", "predictCategory", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "isPredicting", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function2;Z)V", "app_debug"})
public final class AppDialogsKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void ApiKeyDialog(@org.jetbrains.annotations.NotNull
    java.lang.String currentKey, @org.jetbrains.annotations.NotNull
    java.lang.String currentProvider, @org.jetbrains.annotations.NotNull
    java.lang.String currentBaseUrl, @org.jetbrains.annotations.NotNull
    java.lang.String currentModel, @org.jetbrains.annotations.NotNull
    java.lang.String currentAdvicePrompt, @org.jetbrains.annotations.NotNull
    java.lang.String currentAnalysisPrompt, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function6<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSave) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void ManualEntryDialog(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.Double, ? super java.lang.String, ? super java.lang.Boolean, kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super kotlin.coroutines.Continuation<? super java.lang.String>, ? extends java.lang.Object> predictCategory, boolean isPredicting) {
    }
}
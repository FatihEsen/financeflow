package com.financeflow.ai.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState;", "", "()V", "Analyzing", "Error", "Idle", "MissingApiKey", "Success", "Lcom/financeflow/ai/ui/dashboard/DashboardState$Analyzing;", "Lcom/financeflow/ai/ui/dashboard/DashboardState$Error;", "Lcom/financeflow/ai/ui/dashboard/DashboardState$Idle;", "Lcom/financeflow/ai/ui/dashboard/DashboardState$MissingApiKey;", "Lcom/financeflow/ai/ui/dashboard/DashboardState$Success;", "app_release"})
public abstract class DashboardState {
    
    private DashboardState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState$Analyzing;", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "()V", "app_release"})
    public static final class Analyzing extends com.financeflow.ai.ui.dashboard.DashboardState {
        @org.jetbrains.annotations.NotNull
        public static final com.financeflow.ai.ui.dashboard.DashboardState.Analyzing INSTANCE = null;
        
        private Analyzing() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState$Error;", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "errorResId", "", "dynamicValue", "", "(ILjava/lang/String;)V", "getDynamicValue", "()Ljava/lang/String;", "getErrorResId", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_release"})
    public static final class Error extends com.financeflow.ai.ui.dashboard.DashboardState {
        private final int errorResId = 0;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String dynamicValue = null;
        
        public Error(int errorResId, @org.jetbrains.annotations.Nullable
        java.lang.String dynamicValue) {
        }
        
        public final int getErrorResId() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getDynamicValue() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.financeflow.ai.ui.dashboard.DashboardState.Error copy(int errorResId, @org.jetbrains.annotations.Nullable
        java.lang.String dynamicValue) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState$Idle;", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "()V", "app_release"})
    public static final class Idle extends com.financeflow.ai.ui.dashboard.DashboardState {
        @org.jetbrains.annotations.NotNull
        public static final com.financeflow.ai.ui.dashboard.DashboardState.Idle INSTANCE = null;
        
        private Idle() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState$MissingApiKey;", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "()V", "app_release"})
    public static final class MissingApiKey extends com.financeflow.ai.ui.dashboard.DashboardState {
        @org.jetbrains.annotations.NotNull
        public static final com.financeflow.ai.ui.dashboard.DashboardState.MissingApiKey INSTANCE = null;
        
        private MissingApiKey() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/financeflow/ai/ui/dashboard/DashboardState$Success;", "Lcom/financeflow/ai/ui/dashboard/DashboardState;", "messageResId", "", "(I)V", "getMessageResId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_release"})
    public static final class Success extends com.financeflow.ai.ui.dashboard.DashboardState {
        private final int messageResId = 0;
        
        public Success(int messageResId) {
        }
        
        public final int getMessageResId() {
            return 0;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.financeflow.ai.ui.dashboard.DashboardState.Success copy(int messageResId) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}
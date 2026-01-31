package com.financeflow.ai.ui.dashboard;

import com.financeflow.ai.data.local.PreferenceManager;
import com.financeflow.ai.data.local.TransactionDao;
import com.financeflow.ai.data.repository.AiRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<AiRepository> aiRepositoryProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public DashboardViewModel_Factory(Provider<TransactionDao> transactionDaoProvider,
      Provider<AiRepository> aiRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.transactionDaoProvider = transactionDaoProvider;
    this.aiRepositoryProvider = aiRepositoryProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(transactionDaoProvider.get(), aiRepositoryProvider.get(), preferenceManagerProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<TransactionDao> transactionDaoProvider,
      Provider<AiRepository> aiRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new DashboardViewModel_Factory(transactionDaoProvider, aiRepositoryProvider, preferenceManagerProvider);
  }

  public static DashboardViewModel newInstance(TransactionDao transactionDao,
      AiRepository aiRepository, PreferenceManager preferenceManager) {
    return new DashboardViewModel(transactionDao, aiRepository, preferenceManager);
  }
}

package com.financeflow.ai.di;

import android.content.Context;
import com.financeflow.ai.data.repository.AiRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataModule_ProvideAiRepositoryFactory implements Factory<AiRepository> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideAiRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AiRepository get() {
    return provideAiRepository(contextProvider.get());
  }

  public static DataModule_ProvideAiRepositoryFactory create(Provider<Context> contextProvider) {
    return new DataModule_ProvideAiRepositoryFactory(contextProvider);
  }

  public static AiRepository provideAiRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideAiRepository(context));
  }
}

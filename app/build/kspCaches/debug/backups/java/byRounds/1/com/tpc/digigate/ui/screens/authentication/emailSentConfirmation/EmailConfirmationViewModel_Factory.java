package com.tpc.digigate.ui.screens.authentication.emailSentConfirmation;

import com.tpc.digigate.domain.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class EmailConfirmationViewModel_Factory implements Factory<EmailConfirmationViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public EmailConfirmationViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public EmailConfirmationViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static EmailConfirmationViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new EmailConfirmationViewModel_Factory(authRepositoryProvider);
  }

  public static EmailConfirmationViewModel newInstance(AuthRepository authRepository) {
    return new EmailConfirmationViewModel(authRepository);
  }
}

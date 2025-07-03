package com.tpc.digigate.ui.screens.authentication.emailVerification;

import com.tpc.digigate.data.firebase.auth.FirebaseServices;
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
public final class EmailVerificationViewModel_Factory implements Factory<EmailVerificationViewModel> {
  private final Provider<FirebaseServices> authRepositoryProvider;

  public EmailVerificationViewModel_Factory(Provider<FirebaseServices> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public EmailVerificationViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static EmailVerificationViewModel_Factory create(
      Provider<FirebaseServices> authRepositoryProvider) {
    return new EmailVerificationViewModel_Factory(authRepositoryProvider);
  }

  public static EmailVerificationViewModel newInstance(FirebaseServices authRepository) {
    return new EmailVerificationViewModel(authRepository);
  }
}

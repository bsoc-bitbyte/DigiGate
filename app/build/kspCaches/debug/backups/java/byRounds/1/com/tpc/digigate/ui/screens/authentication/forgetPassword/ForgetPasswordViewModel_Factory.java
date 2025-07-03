package com.tpc.digigate.ui.screens.authentication.forgetPassword;

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
public final class ForgetPasswordViewModel_Factory implements Factory<ForgetPasswordViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public ForgetPasswordViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ForgetPasswordViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static ForgetPasswordViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new ForgetPasswordViewModel_Factory(authRepositoryProvider);
  }

  public static ForgetPasswordViewModel newInstance(AuthRepository authRepository) {
    return new ForgetPasswordViewModel(authRepository);
  }
}

package com.tpc.digigate.ui.screens.authentication.register;

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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public RegisterViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public RegisterViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static RegisterViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new RegisterViewModel_Factory(authRepositoryProvider);
  }

  public static RegisterViewModel newInstance(AuthRepository authRepository) {
    return new RegisterViewModel(authRepository);
  }
}

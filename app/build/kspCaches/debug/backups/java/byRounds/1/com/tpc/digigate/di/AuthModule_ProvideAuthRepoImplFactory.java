package com.tpc.digigate.di;

import com.tpc.digigate.data.firebase.auth.FirebaseServices;
import com.tpc.digigate.domain.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthModule_ProvideAuthRepoImplFactory implements Factory<AuthRepository> {
  private final Provider<FirebaseServices> firebaseServicesProvider;

  public AuthModule_ProvideAuthRepoImplFactory(
      Provider<FirebaseServices> firebaseServicesProvider) {
    this.firebaseServicesProvider = firebaseServicesProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepoImpl(firebaseServicesProvider.get());
  }

  public static AuthModule_ProvideAuthRepoImplFactory create(
      Provider<FirebaseServices> firebaseServicesProvider) {
    return new AuthModule_ProvideAuthRepoImplFactory(firebaseServicesProvider);
  }

  public static AuthRepository provideAuthRepoImpl(FirebaseServices firebaseServices) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthRepoImpl(firebaseServices));
  }
}

package com.tpc.digigate.data.repository;

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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<FirebaseServices> firebaseServicesProvider;

  public AuthRepositoryImpl_Factory(Provider<FirebaseServices> firebaseServicesProvider) {
    this.firebaseServicesProvider = firebaseServicesProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(firebaseServicesProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(
      Provider<FirebaseServices> firebaseServicesProvider) {
    return new AuthRepositoryImpl_Factory(firebaseServicesProvider);
  }

  public static AuthRepositoryImpl newInstance(FirebaseServices firebaseServices) {
    return new AuthRepositoryImpl(firebaseServices);
  }
}

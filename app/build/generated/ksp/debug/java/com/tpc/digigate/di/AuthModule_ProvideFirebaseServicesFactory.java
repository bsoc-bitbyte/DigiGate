package com.tpc.digigate.di;

import com.tpc.digigate.data.firebase.auth.FirebaseServices;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AuthModule_ProvideFirebaseServicesFactory implements Factory<FirebaseServices> {
  @Override
  public FirebaseServices get() {
    return provideFirebaseServices();
  }

  public static AuthModule_ProvideFirebaseServicesFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseServices provideFirebaseServices() {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideFirebaseServices());
  }

  private static final class InstanceHolder {
    static final AuthModule_ProvideFirebaseServicesFactory INSTANCE = new AuthModule_ProvideFirebaseServicesFactory();
  }
}

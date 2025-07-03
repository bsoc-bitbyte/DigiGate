package com.tpc.digigate.data.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
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
public final class FirebaseServices_Factory implements Factory<FirebaseServices> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public FirebaseServices_Factory(Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public FirebaseServices get() {
    return newInstance(firebaseAuthProvider.get());
  }

  public static FirebaseServices_Factory create(Provider<FirebaseAuth> firebaseAuthProvider) {
    return new FirebaseServices_Factory(firebaseAuthProvider);
  }

  public static FirebaseServices newInstance(FirebaseAuth firebaseAuth) {
    return new FirebaseServices(firebaseAuth);
  }
}

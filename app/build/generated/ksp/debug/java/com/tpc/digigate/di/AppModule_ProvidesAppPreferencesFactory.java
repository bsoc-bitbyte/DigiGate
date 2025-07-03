package com.tpc.digigate.di;

import android.content.Context;
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvidesAppPreferencesFactory implements Factory<AppPreferences> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvidesAppPreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppPreferences get() {
    return providesAppPreferences(contextProvider.get());
  }

  public static AppModule_ProvidesAppPreferencesFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvidesAppPreferencesFactory(contextProvider);
  }

  public static AppPreferences providesAppPreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesAppPreferences(context));
  }
}

package com.tpc.digigate.ui.screens.settings;

import com.tpc.digigate.domain.repository.appPreferences.AppPreferences;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<AppPreferences> appPreferencesProvider;

  public SettingsViewModel_Factory(Provider<AppPreferences> appPreferencesProvider) {
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(appPreferencesProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<AppPreferences> appPreferencesProvider) {
    return new SettingsViewModel_Factory(appPreferencesProvider);
  }

  public static SettingsViewModel newInstance(AppPreferences appPreferences) {
    return new SettingsViewModel(appPreferences);
  }
}

package com.tpc.digigate.referenceCodes.ref1;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FormViewModel_Factory implements Factory<FormViewModel> {
  @Override
  public FormViewModel get() {
    return newInstance();
  }

  public static FormViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FormViewModel newInstance() {
    return new FormViewModel();
  }

  private static final class InstanceHolder {
    static final FormViewModel_Factory INSTANCE = new FormViewModel_Factory();
  }
}

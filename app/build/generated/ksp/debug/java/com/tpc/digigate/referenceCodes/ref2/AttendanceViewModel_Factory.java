package com.tpc.digigate.referenceCodes.ref2;

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
public final class AttendanceViewModel_Factory implements Factory<AttendanceViewModel> {
  @Override
  public AttendanceViewModel get() {
    return newInstance();
  }

  public static AttendanceViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AttendanceViewModel newInstance() {
    return new AttendanceViewModel();
  }

  private static final class InstanceHolder {
    static final AttendanceViewModel_Factory INSTANCE = new AttendanceViewModel_Factory();
  }
}

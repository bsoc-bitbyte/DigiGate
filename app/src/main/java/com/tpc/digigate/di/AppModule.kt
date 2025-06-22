package com.tpc.digigate.di

import android.content.Context
import com.tpc.digigate.data.local.AppPreferencesImpl
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAppPreferences(@ApplicationContext context: Context): AppPreferences =
        AppPreferencesImpl(context)
}
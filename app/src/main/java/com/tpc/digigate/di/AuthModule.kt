package com.tpc.digigate.di

import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.data.repository.AuthRepositoryImpl
import com.tpc.digigate.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseServices(): FirebaseServices {
        return FirebaseServices()
    }

    @Provides
    @Singleton
    fun provideAuthRepoImpl(firebaseServices: FirebaseServices): AuthRepository {
        return AuthRepositoryImpl(firebaseServices)
    }


}
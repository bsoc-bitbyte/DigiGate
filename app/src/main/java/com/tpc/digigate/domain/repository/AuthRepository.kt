package com.tpc.digigate.domain.repository

import android.content.Context
import com.tpc.digigate.domain.model.AuthResult
import com.tpc.digigate.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticated(): Boolean

    fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResult>

    suspend fun signInWithGoogle(context: Context): Flow<AuthResult>

    fun createUserWithEmailAndPassword(
        email: String, password: String
    ): Flow<AuthResult>

    fun sendPasswordResetEmail(email: String): Flow<AuthResult>

    fun sendEmailVerificationMail(): Flow<AuthResult>

    fun verifyEmail(code: String): Flow<AuthResult>

    fun updatePassword(code: String, newPassword: String): Flow<AuthResult>

    fun signOut()
}
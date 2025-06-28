package com.tpc.digigate.data.repository

import android.content.Context
import android.util.Log
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.data.firebase.auth.GoogleSignInClient
import com.tpc.digigate.domain.model.AuthResult
import com.tpc.digigate.domain.model.User
import com.tpc.digigate.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseServices: FirebaseServices) :
    AuthRepository {

    override fun isUserAuthenticated(): Boolean {
        return firebaseServices.isUserAuthenticated()
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResult> {
        return firebaseServices.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithGoogle(context: Context): Flow<AuthResult> = flow {
        val googleSignInClient = GoogleSignInClient(context)
        val idToken = googleSignInClient.signIn()
        Log.i("LoginViewModel", "Google ID Token: $idToken")
        if (idToken == null) {
            emit(AuthResult.Error("Google Sign-In failed"))
        } else {
            firebaseServices.signInWithGoogle(idToken).collect { it ->
                emit(it)
            }
        }

    }

    override fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResult> {
        return firebaseServices.createUserWithEmailAndPassword(email, password)
    }

    override fun sendPasswordResetEmail(email: String): Flow<AuthResult> {
        return firebaseServices.sendPasswordResetEmail(email)
    }

    override fun sendEmailVerificationMail(): Flow<AuthResult> {
        return firebaseServices.sendEmailVerificationMail()
    }

    override fun verifyEmail(code: String): Flow<AuthResult> {
        return firebaseServices.verifyEmail(code)
    }

    override fun updatePassword(
        code: String,
        newPassword: String
    ): Flow<AuthResult> {
        return firebaseServices.updatePassword(code, newPassword)
    }

    override fun signOut() {
        return firebaseServices.signOut()
    }
}
package com.tpc.digigate.data.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tpc.digigate.domain.model.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseServices @Inject constructor(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()) {

    fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResult> = flow {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user

            if (firebaseUser != null) {
                if (firebaseUser.isEmailVerified) {
                    emit(AuthResult.Success("Success"))
                } else {
                    emit(AuthResult.VerificationNeeded(email, "Email Verification Needed"))
                }
            } else {
                emit(AuthResult.Error("Authentication failed"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Authentication failed"))
        }
    }

    fun signInWithGoogle(idToken: String): Flow<AuthResult> = flow {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            val firebaseUser = result.user

            if (firebaseUser != null) {
                emit(
                    AuthResult.Success(
                        "Success"
                    )
                )
            } else {
                emit(AuthResult.Error("Google authentication failed"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Google authentication failed"))
        }
    }

    fun createUserWithEmailAndPassword(
        email: String, password: String
    ): Flow<AuthResult> = flow {
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user

            if (firebaseUser != null) {
                emit(AuthResult.VerificationNeeded(email, "Verification Needed"))
            } else {
                emit(AuthResult.Error("User creation failed"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "User creation failed"))
        }
    }

    fun sendPasswordResetEmail(email: String): Flow<AuthResult> = flow {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(AuthResult.Success("Email Sent"))
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Failed to send password reset email"))
        }
    }

    fun sendEmailVerificationMail(): Flow<AuthResult> = flow {
        try {
            val firebaseUser = firebaseAuth.currentUser

            if (firebaseUser != null) {
                firebaseUser.sendEmailVerification().await()
                emit(AuthResult.Success("Email Sent"))
            } else {
                emit(AuthResult.Error("No user is signed in"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Failed to send email verification"))
        }
    }

    fun updatePassword(code: String, newPassword: String): Flow<AuthResult> = flow {
        try {
            firebaseAuth.confirmPasswordReset(code, newPassword).await()
            emit(AuthResult.Success("Password updated successfully"))
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Failed to update password"))
        }
    }

    fun verifyEmail(code: String): Flow<AuthResult> = flow {
        try {
            firebaseAuth.applyActionCode(code).await()
            emit(AuthResult.Success("Email Verified"))
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Email verification failed"))
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}
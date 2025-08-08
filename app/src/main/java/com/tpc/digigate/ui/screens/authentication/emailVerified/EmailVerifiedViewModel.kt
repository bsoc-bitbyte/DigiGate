package com.tpc.digigate.ui.screens.authentication.emailVerified

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tpc.digigate.EmailVerificationDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EmailVerifiedViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EmailVerifiedUIState())
    val uiState: StateFlow<EmailVerifiedUIState> = _uiState.asStateFlow()


    fun checkVerificationStatus(toMainApp: () -> Unit = {}) {
        val mode = EmailVerificationDetails.mode
        val oobCode = EmailVerificationDetails.oobCode
        val firebaseAuth = FirebaseAuth.getInstance()
        var userAuthentication = firebaseAuth.currentUser?.isEmailVerified
        if (mode == "verifyEmail" && !oobCode.isNullOrBlank()) {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    title = "",
                    message = "",
                    isDone = false
                )
            }
            firebaseAuth.applyActionCode(oobCode)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        firebaseAuth.currentUser?.reload()?.addOnCompleteListener {
                            userAuthentication =
                                firebaseAuth.currentUser?.isEmailVerified == true
                            if (userAuthentication) {
                                viewModelScope.launch {
                                    _uiState.update {
                                        it.copy(
                                            title = "Email verified successfully",
                                            message = "Your email has been verified!",
                                            isDone = true,
                                            isLoading = false
                                        )
                                    }
                                    resetEmailVerificationDetails()
                                    delay(1000)
                                    toMainApp()
                                }
                            } else {
                                _uiState.update {
                                    it.copy(
                                        title = "Email Verification Failed",
                                        message = "We couldn't confirm your email verification. Please try again.",
                                        isLoading = false
                                    )
                                }
                                resetEmailVerificationDetails()
                            }
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                title = "Invalid or expired verification link",
                                message = "This link is no longer valid. Please request a new one or use the latest link sent to your email.",
                                isLoading = false
                            )
                        }
                        resetEmailVerificationDetails()
                    }
                }
        }
    }

    fun resetEmailVerificationDetails() {
        EmailVerificationDetails.mode = ""
        EmailVerificationDetails.oobCode = ""
    }


}
package com.tpc.digigate.ui.screens.authentication.emailVerificationEmailSentConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.domain.model.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EmailConfirmationViewModel @Inject constructor(
    private val authRepository: FirebaseServices,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmailConfirmationUIState())
    val uiState: StateFlow<EmailConfirmationUIState> = _uiState.asStateFlow()

    private var resetTimer: Job? = null

    fun toastMessageShown() {
        _uiState.update { it.copy(message = null) }
    }

    init {
        resetTimer?.cancel()
        sendVerificationEmail()
    }

    private fun startCountdown() {
        resetTimer?.cancel()
        resetTimer = viewModelScope.launch {
            for (i in 30 downTo 1) {
        viewModelScope.launch {
            for (i in 60 downTo 1) {
                _uiState.update { it.copy(canResend = false, countdown = i) }
                delay(1000)
            }
            _uiState.update { it.copy(canResend = true) }
        }
    }

    fun sendVerificationEmail() {
        _uiState.update {
            it.copy(
                isLoading = true,
                message = null,
                canResend = false
            )
        }
        viewModelScope.launch {
            try {
                authRepository.sendEmailVerificationMail().collect { result ->
                    when (result) {
                        is AuthResult.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    message = result.message,
                                    canResend = false
                                )
                            }
                            startCountdown()
                        }

                        is AuthResult.Error -> {
                            val errorMessage = if (result.message.contains("blocked") == true) {
                                "Please wait before requesting another email."
                            } else {
                                "Unable to send verification email. Please try again later."
                            }
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    message = errorMessage,
                                    canResend = true,
                                )
                            }
                        }

                        else -> Unit
            authRepository.sendEmailVerificationMail().collect {authResult ->
                _uiState.update { it.copy(isLoading = false, message = authResult.message, canResend = false) }
                when (authResult) {
                    is AuthResult.Success -> {
                        startCountdown()
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = e.message,
                        canResend = true
                    )
                }
            }
        }
    }
}

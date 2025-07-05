package com.tpc.digigate.ui.screens.authentication.emailVerificationEmailSentConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.domain.model.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
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

    init {
        sendVerificationEmail()
    }

    fun toastMessageShown(){
        _uiState.update { it.copy(message = null) }
    }

    private fun startCountdown() {
        viewModelScope.launch {
            for (i in 60 downTo 1) {
                _uiState.update { it.copy(canResend = false, countdown = i) }
                delay(1000)
            }
            _uiState.update { it.copy(canResend = true) }
        }
    }

    fun sendVerificationEmail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            authRepository.sendEmailVerificationMail().collect {authResult ->
                _uiState.update { it.copy(isLoading = false, message = authResult.message, canResend = false) }
                when (authResult) {
                    is AuthResult.Success -> {
                        startCountdown()
                    }

                    else -> Unit
                }
            }
        }
    }
}
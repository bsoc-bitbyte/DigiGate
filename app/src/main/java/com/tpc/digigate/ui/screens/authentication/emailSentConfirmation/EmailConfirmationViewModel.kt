package com.tpc.digigate.ui.screens.authentication.emailSentConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.domain.model.AuthResult
import com.tpc.digigate.domain.repository.AuthRepository
import com.tpc.digigate.ui.screens.authentication.forgetPassword.ForgetScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailConfirmationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _emailConfirmationUiState = MutableStateFlow(EmailConfirmationUiState())
    val emailConfirmationUiState: StateFlow<EmailConfirmationUiState> =
        _emailConfirmationUiState.asStateFlow()

    private var resetTimer: Job? = null

    fun setEmail(email: String) {
        _emailConfirmationUiState.update { it.copy(email = email) }
    }

    fun resetState() {
        resetTimer?.cancel()
        _emailConfirmationUiState.value = EmailConfirmationUiState()
        countDownStart()
    }


    fun clearToast() {
        _emailConfirmationUiState.update {
            it.copy(
                toastMessage = null
            )
        }
    }

    fun countDownStart() {
        resetTimer?.cancel()
        resetTimer = viewModelScope.launch {
            for (i in 30 downTo 1) {
                _emailConfirmationUiState.update {
                    it.copy(
                        canResend = false,
                        countdown = i
                    )
                }
                delay(1000)
            }
            _emailConfirmationUiState.update {
                it.copy(
                    canResend = true,
                )
            }
        }

    }

    fun onResendClicked() {
        val email = _emailConfirmationUiState.value.email
        _emailConfirmationUiState.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
                canResend = false
            )
        }
        if (email != null) {
            viewModelScope.launch {
                authRepository.sendPasswordResetEmail(email).collect { result ->
                    _emailConfirmationUiState.update {
                        it.copy(
                            isLoading = false,
                            toastMessage = result.message,
                        )
                    }
                    when (result) {
                        is AuthResult.Success -> {
                            countDownStart()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}
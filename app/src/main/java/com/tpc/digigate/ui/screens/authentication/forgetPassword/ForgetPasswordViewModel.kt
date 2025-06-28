package com.tpc.digigate.ui.screens.authentication.forgetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.domain.model.AuthResult
import com.tpc.digigate.domain.repository.AuthRepository
import com.tpc.digigate.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _forgetPasswordUiState = MutableStateFlow(ForgetScreenUiState())
    val forgetPasswordUiState: StateFlow<ForgetScreenUiState> = _forgetPasswordUiState.asStateFlow()


    fun emailInput(input: String) {
        _forgetPasswordUiState.update {
            it.copy(
                email = input,
                isEmailError = false
            )
        }
    }

    fun resetState() {
        _forgetPasswordUiState.value = ForgetScreenUiState()
    }

    fun clearToast() {
        _forgetPasswordUiState.update {
            it.copy(
                toastMessage = null
            )
        }
    }

    fun onSendClicked(toEmailConfirmationPage: (String) -> Unit) {
        val email = _forgetPasswordUiState.value.email

        if (email.isBlank()) {
            _forgetPasswordUiState.update {
                it.copy(
                    toastMessage = "Email cannot be empty"
                )
            }
            return
        }
        _forgetPasswordUiState.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
            )
        }
        Validator.isValidEmail(
            email, onSuccess = {
                viewModelScope.launch {
                    authRepository.sendPasswordResetEmail(email).collect { result ->
                        _forgetPasswordUiState.update {
                            it.copy(
                                isLoading = false,
                                toastMessage = result.message
                            )
                        }
                        when (result) {
                            is AuthResult.Success -> {
                                toEmailConfirmationPage(email)
                            }

                            else -> Unit
                        }
                    }
                }
            },
            onFailure = { error ->
                _forgetPasswordUiState.update {
                    it.copy(
                        isLoading = false,
                        toastMessage = error,
                        isEmailError = true
                    )
                }
            }
        )
    }
}
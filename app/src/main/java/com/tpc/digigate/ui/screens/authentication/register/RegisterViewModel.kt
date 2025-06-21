package com.tpc.digigate.ui.screens.authentication.register

import androidx.lifecycle.ViewModel
import com.tpc.digigate.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    private val _registerUiState = MutableStateFlow(RegisterUIState())
    val registerUiState: StateFlow<RegisterUIState> = _registerUiState.asStateFlow()

    fun emailInput(email: String) {
        _registerUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun passwordInput(password: String) {
        _registerUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun isEnabled(): Boolean {
        val email = _registerUiState.value.email
        val password = _registerUiState.value.password

        if (email.isBlank() || password.isBlank()) {
            return false
        }
        else
            return true
    }



    fun toastMessageShown() {
        _registerUiState.update {
            it.copy(
                toastMessage = null
            )
        }
    }
    fun onClickRegister() {
        val email = _registerUiState.value.email
        val password = _registerUiState.value.password

        val passwordValid: Boolean = password.length >= 6

        if (!passwordValid) {
            _registerUiState.update {
                it.copy(
                    toastMessage = "Password is too short. It should be atleast 6 characters or more."
                )
            }
        }

        Validator.isValidEmail(email,onSuccess= {message->
            if (passwordValid){
                _registerUiState.update {
                    it.copy(
                        toastMessage = message,
                        isLoading = true
                    )
                }
            }
        }, onFailure = {error->
                _registerUiState.update {
                    it.copy(
                        toastMessage = error
                    )
                }
            }
        )
    }
}
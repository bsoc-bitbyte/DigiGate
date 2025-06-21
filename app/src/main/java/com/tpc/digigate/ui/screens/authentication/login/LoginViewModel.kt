package com.tpc.digigate.ui.screens.authentication.login

import android.R
import androidx.lifecycle.ViewModel
import com.tpc.digigate.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUIState())
    val loginUiState: StateFlow<LoginUIState> = _loginUiState.asStateFlow()

    fun emailInput(email: String) {
        _loginUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun toastMessageShown() {
        _loginUiState.update {
            it.copy(
                toastMessage = null
            )
        }
    }

    fun passwordInput(password: String) {
        _loginUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun isEnabled(): Boolean {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password

        if (email.isBlank() || password.isBlank()) {
            return false
        }
        else
            return true
    }


    fun onClickLogin() {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password

        val passwordValid: Boolean = password.length >= 6

        if (!passwordValid) {
            _loginUiState.update {
                it.copy(
                    toastMessage = "Password is too short. It should be atleast 6 characters or more."
                )
            }
        }
        Validator.isValidEmail(email,onSuccess= {message->
            if (passwordValid){
                _loginUiState.update {
                    it.copy(
                        toastMessage = message,
                        isLoading = true
                    )
                }
            }
        }, onFailure = {error->
            _loginUiState.update {
                it.copy(
                    toastMessage = error
                )
            }
        }
        )

    }
}
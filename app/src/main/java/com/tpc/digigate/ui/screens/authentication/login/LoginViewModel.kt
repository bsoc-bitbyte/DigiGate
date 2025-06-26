package com.tpc.digigate.ui.screens.authentication.login

import android.content.Context
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
class LoginViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {
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

        return !(email.isBlank() || password.isBlank())
    }


    fun onClickLogin(goToMainApp: () -> Unit, goToEmailVerificationScreen: () -> Unit) {
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
        Validator.isValidEmail(email, onSuccess = {
            if (passwordValid) {
                _loginUiState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                viewModelScope.launch {
                    authRepository.signInWithEmailAndPassword(
                        _loginUiState.value.email,
                        _loginUiState.value.password
                    ).collect { authResult ->
                        _loginUiState.update {
                            it.copy(
                                isLoading = false,
                                toastMessage = authResult.message
                            )
                        }
                        when (authResult) {

                            is AuthResult.Success -> {
                                toastMessageShown()
                                goToMainApp()
                            }

                            is AuthResult.VerificationNeeded -> {
                                goToEmailVerificationScreen()
                            }

                            else -> {

                            }
                        }
                    }
                }

            }
        }, onFailure = { error ->
            _loginUiState.update {
                it.copy(
                    toastMessage = error
                )
            }
        }
        )

    }

    fun googleSignIn(
        context: Context,
        goToMainApp: () -> Unit,
        goToEmailVerificationScreen: () -> Unit
    ) {
        _loginUiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authRepository.signInWithGoogle(context).collect { authResult ->
                _loginUiState.update { it.copy(isLoading = false) }
                println(authResult.message)
                _loginUiState.update { it.copy(toastMessage = authResult.message) }
                when (authResult) {
                    is AuthResult.Success -> {
                        goToMainApp()
                    }

                    is AuthResult.VerificationNeeded -> {
                        goToEmailVerificationScreen()
                    }

                    else -> {

                    }
                }
            }
        }
    }
}
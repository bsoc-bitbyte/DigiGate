package com.tpc.digigate.ui.screens.authentication.register

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
class RegisterViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {
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
        } else
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

        Validator.isValidEmail(email, onSuccess = {
            if (passwordValid) {
                _registerUiState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                viewModelScope.launch {
                    authRepository.createUserWithEmailAndPassword(
                        _registerUiState.value.email,
                        _registerUiState.value.password
                    ).collect { authResult ->
                        _registerUiState.update {
                            it.copy(
                                isLoading = false,
                                toastMessage = authResult.message
                            )
                        }
                        when (authResult) {
                            is AuthResult.VerificationNeeded -> {
                                _registerUiState.update {
                                    it.copy(isLoading = false, needsVerification = true)
                                }
                            }

                            else -> {

                            }
                        }

                    }
                }

            }
        }, onFailure = { error ->
            _registerUiState.update {
                it.copy(
                    toastMessage = error
                )
            }
        }
        )
    }

    fun googleSignIn(context: Context, goToMainApp: () -> Unit) {
        _registerUiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authRepository.signInWithGoogle(context).collect { authResult ->
                _registerUiState.update { it.copy(isLoading = false) }
                println(authResult.message)
                _registerUiState.update { it.copy(toastMessage = authResult.message) }
                when (authResult) {
                    is AuthResult.Success -> {
                        _registerUiState.update { it.copy(toastMessage = null) }
                        goToMainApp()
                    }

                    is AuthResult.VerificationNeeded -> {
                        _registerUiState.update {
                            it.copy(isLoading = false, needsVerification = true)
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}
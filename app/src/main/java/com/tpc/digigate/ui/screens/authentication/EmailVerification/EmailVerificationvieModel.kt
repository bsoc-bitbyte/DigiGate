package com.tpc.digigate.ui.screens.authentication.EmailVerification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.domain.model.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class EmailVerificationViewModel @Inject constructor(
    private val authRepository: FirebaseServices,
) : ViewModel() {

    var uiState by mutableStateOf(EmailVerificationUiState())
        private set

    init {
        sendVerificationEmail()
    }

    private fun startCountdown() {
        viewModelScope.launch {
            for (i in 30 downTo 1) {
                uiState = uiState.copy(canresend = false, countdown = i)
                delay(1000)
            }
            uiState = uiState.copy(canresend = true)
        }
    }

    fun sendVerificationEmail(onResult: ((AuthResult) -> Unit)? = null) {
        viewModelScope.launch {
            uiState = uiState.copy(isloading = true, errorMessage = null, emailSent = false)
            authRepository.sendEmailVerificationMail().collect {
                when (it) {
                    is AuthResult.Success -> {
                        uiState = uiState.copy(
                            isloading = false,
                            emailSent = true,
                            errorMessage = null,
                            canresend = false
                        )
                        startCountdown()
                        onResult?.invoke(it)
                    }

                    is AuthResult.Error -> {
                        uiState = uiState.copy(isloading = false, errorMessage = it.message)
                        onResult?.invoke(it)
                    }

                    else -> Unit
                }
            }
        }
    }
}
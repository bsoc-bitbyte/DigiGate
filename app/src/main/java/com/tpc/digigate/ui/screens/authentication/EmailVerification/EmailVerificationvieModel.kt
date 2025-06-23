package com.tpc.digigate.ui.screens.authentication.EmailVerification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.domain.model.AuthResult
import kotlinx.coroutines.launch

class EmailVerificationViewModel(private val firebaseServices: FirebaseServices) : ViewModel() {
    var isloading by mutableStateOf(false)

    fun sendVerificationEmail(onResult: (AuthResult) -> Unit) {
        viewModelScope.launch {
            isloading = true
            firebaseServices.sendEmailVerificationMail().collect {
                onResult(it)
            }
            isloading = false
        }
    }
}
package com.tpc.digigate.ui.screens.authentication.forgetPassword

data class ForgetScreenUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val toastMessage: String? = null,
    val isEmailError: Boolean = false,
)

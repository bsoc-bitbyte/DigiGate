package com.tpc.digigate.ui.screens.authentication.EmailVerification

data class EmailVerificationUiState(
    val isloading: Boolean = false,
    val canresend: Boolean = false,
    val countdown: Int = 30,
    val errorMessage: String? = null,
    val emailSent: Boolean = false,
)
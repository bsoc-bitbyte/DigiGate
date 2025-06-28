package com.tpc.digigate.ui.screens.authentication.emailSentConfirmation

data class EmailConfirmationUiState(
    val email :String ? = null,
    val isLoading: Boolean = false,
    val canResend: Boolean = false,
    val countdown: Int = 30,
    val toastMessage: String? = null,
)

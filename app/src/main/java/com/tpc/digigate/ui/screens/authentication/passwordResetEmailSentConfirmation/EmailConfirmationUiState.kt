package com.tpc.digigate.ui.screens.authentication.passwordResetEmailSentConfirmation

data class EmailConfirmationUiState(
    val email :String ? = null,
    val isLoading: Boolean = false,
    val canResend: Boolean = false,
    val countdown: Int = 60,
    val toastMessage: String? = null,
)

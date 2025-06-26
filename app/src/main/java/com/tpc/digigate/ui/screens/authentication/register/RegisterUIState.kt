package com.tpc.digigate.ui.screens.authentication.register

data class RegisterUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val email: String = "",
    val password: String = "",
    val toastMessage: String? = null,
    val errorMessage: String = "",
)

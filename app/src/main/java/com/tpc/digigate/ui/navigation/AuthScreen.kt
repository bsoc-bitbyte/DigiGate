package com.tpc.digigate.ui.navigation

sealed class AuthScreen {

    object Login : AuthScreen()

    object OnBoarding : AuthScreen()

    object Register : AuthScreen()

    object EmailVerification : AuthScreen()

    object ForgetPasswordScreen : AuthScreen()

    data class EmailSentConfirmation(val email: String) : AuthScreen()

    data class EmailVerified(val title: String, val message: String) : AuthScreen()
}
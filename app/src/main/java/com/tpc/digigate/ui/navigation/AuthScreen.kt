package com.tpc.digigate.ui.navigation

sealed class AuthScreen {

    object Login : AuthScreen()

    object OnBoarding : AuthScreen()

    object Register : AuthScreen()
}
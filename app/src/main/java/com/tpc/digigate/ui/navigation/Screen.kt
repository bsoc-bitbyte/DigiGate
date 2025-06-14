package com.tpc.digigate.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Settings : Screen("settings")

    object History : Screen("history")

}
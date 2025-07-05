package com.tpc.digigate.ui.navigation

sealed class Screen() {
    data object Home : Screen()

    data object History : Screen()

    data object Profile : Screen()

    data object Settings : Screen()
}

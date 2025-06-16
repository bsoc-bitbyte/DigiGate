package com.tpc.digigate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val title: String,
    val iconFilled: ImageVector,
    val iconOut: ImageVector
) {
    data object Home : Screen("Home", Icons.Filled.Home, Icons.Outlined.Home)

    data object History : Screen("History", Icons.Filled.History, Icons.Outlined.History)

    data object Profile : Screen("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

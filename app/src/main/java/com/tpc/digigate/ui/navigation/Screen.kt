package com.tpc.digigate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(
    val title: String,
    val iconFilled: ImageVector,
    val iconOut: ImageVector,
    val place: Int
) {
    @Serializable
    data object Home: Screen("Home", Icons.Filled.Home, Icons.Outlined.Home, 2)

    @Serializable
    data object History: Screen("History", Icons.Filled.History, Icons.Outlined.History, 1)

    @Serializable
    data object Profile: Screen("Profile", Icons.Filled.Person, Icons.Outlined.Person, 3)

}

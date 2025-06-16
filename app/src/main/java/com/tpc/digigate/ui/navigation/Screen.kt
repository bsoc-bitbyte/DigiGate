//package com.tpc.digigate.ui.navigation
//
//import androidx.navigation3.runtime.NavKey
//import kotlinx.serialization.Serializable
//
//sealed class Screen: NavKey {
//
//    @Serializable
//    data object Home: Screen()
//
//    @Serializable
//    data object History: Screen()
//
//    @Serializable
//    data object Profile: Screen()
//    // data objects go here
//}

package com.tpc.digigate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val title: String,
    val iconFilled: ImageVector,
    val iconOut: ImageVector
) {
    object Home : Screen("Home", Icons.Filled.Home, Icons.Outlined.Home)
    object History : Screen("History", Icons.Filled.History, Icons.Outlined.History)
    object Profile : Screen("Profile", Icons.Filled.Person, Icons.Outlined.Person)

    companion object {
        val items = listOf(Home, History, Profile)
    }
}

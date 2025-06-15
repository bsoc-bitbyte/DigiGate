package com.tpc.digigate.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen: NavKey {

    @Serializable
    data object Home: Screen()

    @Serializable
    data object History: Screen()

    @Serializable
    data object Profile: Screen()
    // data objects go here
}
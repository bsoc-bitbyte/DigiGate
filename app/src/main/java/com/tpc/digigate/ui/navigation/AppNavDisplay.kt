package com.tpc.digigate.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tpc.digigate.ui.components.BottomNavigationBar
import com.tpc.digigate.ui.screens.history.HistoryScreen
import com.tpc.digigate.ui.screens.home.HomeScreenLayout
import com.tpc.digigate.ui.screens.profile.ProfileScreen
//import com.tpc.digigate.ui.components.Destinations

@Composable
fun AppNavDisplay() {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }
    val currentScreen = backStack.last()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selected = currentScreen,
                onItemClick = {
                    if (it != currentScreen) {
                        backStack.add(it)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            when (currentScreen) {
                is Screen.Home -> HomeScreenLayout()
                is Screen.History -> HistoryScreen()
                is Screen.Profile -> ProfileScreen()
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppNavDisplayPreview() {
    AppNavDisplay()
}


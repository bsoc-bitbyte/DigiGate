package com.tpc.digigate.ui.navigation

import androidx.compose.foundation.layout.Box
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
import androidx.navigation3.ui.NavDisplay
import com.tpc.digigate.ui.components.BottomNavigationBar
import com.tpc.digigate.ui.screens.history.HistoryScreen
import com.tpc.digigate.ui.screens.home.HomeScreenLayout
import com.tpc.digigate.ui.screens.profile.ProfileScreen

@Composable
fun AppNavDisplay() {

    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }

    if(backStack.isEmpty()) {
        backStack.add(Screen.Home)
    }

    val currentScreen = backStack.lastOrNull() ?: Screen.Home

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
            NavDisplay(
                backStack = backStack,
                onBack = {
                    backStack.removeLastOrNull()
                         },
                entryProvider = entryProvider {

                    entry<Screen.Home> {
                        HomeScreenLayout()
                    }

                    entry<Screen.History> {
                        HistoryScreen()
                    }

                    entry<Screen.Profile> {
                        ProfileScreen()
                    }

                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppNavDisplayPreview() {
    AppNavDisplay()
}


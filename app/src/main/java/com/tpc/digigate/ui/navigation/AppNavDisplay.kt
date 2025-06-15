package com.tpc.digigate.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
import com.tpc.digigate.ui.components.Destinations

@Composable
fun AppNavDisplay(){

    val backStack = rememberNavBackStack<Screen>(Screen.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.Home> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        HomeScreenLayout()
                    }
                    BottomNavigationBar(
                        selected = Destinations.Home,
                        onItemClick = {
                            when (it) {
                                Destinations.Home -> backStack.add(Screen.Home)
                                Destinations.History -> backStack.add(Screen.History)
                                Destinations.Profile -> backStack.add(Screen.Profile)
                            }
                        }
                    )
                }
            }
            entry<Screen.History> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        HistoryScreen()
                    }
                    BottomNavigationBar(
                        selected = Destinations.History,
                        onItemClick = {
                            when (it) {
                                Destinations.Home -> backStack.add(Screen.Home)
                                Destinations.History -> backStack.add(Screen.History)
                                Destinations.Profile -> backStack.add(Screen.Profile)
                            }
                        }
                    )
                }
            }
            entry<Screen.Profile> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProfileScreen()
                    }
                    BottomNavigationBar(
                        selected = Destinations.Profile,
                        onItemClick = {
                            when (it) {
                                Destinations.Home -> backStack.add(Screen.Home)
                                Destinations.History -> backStack.add(Screen.History)
                                Destinations.Profile -> backStack.add(Screen.Profile)
                            }
                        }
                    )
                }
            }
        }

    )

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppNavDisplayPreview() {
    AppNavDisplay()
}


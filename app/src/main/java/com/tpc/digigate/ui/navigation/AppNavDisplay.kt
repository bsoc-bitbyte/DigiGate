package com.tpc.digigate.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tpc.digigate.ui.components.BottomNavigationBar
import com.tpc.digigate.ui.screens.history.HistoryScreen
import com.tpc.digigate.ui.screens.home.HomeScreenLayout
import com.tpc.digigate.ui.screens.profile.ProfileScreen

@Composable
fun AppNavDisplay(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onDestinationClicked = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreenLayout()
            }
            composable(Screen.Settings.route) {
                ProfileScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppNavDisplayPreview() {
    AppNavDisplay()
}

package com.tpc.digigate.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var previousScreen by remember { mutableStateOf<Screen?>(null) }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selected = backStack.last(),
                onItemClick = {
                    previousScreen = backStack.lastOrNull()
                    if (it != previousScreen) {
                        if (backStack.size > 1) backStack.removeLastOrNull()
                        backStack.add(it)
                    }
                }
            )
        }
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            transitionSpec = {
                val from = previousScreen
                val to = backStack.lastOrNull()

                val forward = when {
                    from == Screen.Home && to == Screen.Profile -> true
                    from == Screen.History && to == Screen.Home -> true
                    from == Screen.History && to == Screen.Profile -> true
                    else -> false
                }

                if (forward) {
                    slideInHorizontally { it } + fadeIn() togetherWith
                            slideOutHorizontally { -it } + fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith
                            slideOutHorizontally { it } + fadeOut()
                }
            },
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


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppNavDisplayPreview() {
    AppNavDisplay()
}


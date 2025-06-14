package com.tpc.digigate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.navigation.Screen

enum class Destinations(
    val title: String,
    val iconFilled: ImageVector,
    val iconOut: ImageVector
) {
    Home("Home", Icons.Filled.Home, Icons.Outlined.Home),
    Settings("Settings", Icons.Filled.Settings, Icons.Outlined.Settings),
    History("History", Icons.Filled.History, Icons.Outlined.History)
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onDestinationClicked: (String) -> Unit
) {
    val destinationList = listOf(
        Destinations.History,
        Destinations.Home,
        Destinations.Settings
    )

    val selectedIndex = rememberSaveable { mutableStateOf(1) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding() + 16.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.navigationBarsPadding()
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(60.dp)
            ) {
                val itemWidth = this.maxWidth / destinationList.size
                val animatedPosition by animateDpAsState(
                    targetValue = ((selectedIndex.value - 1) * (itemWidth.value + 2)).dp,
                    animationSpec = tween(durationMillis = 200), label = ""
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFB8CECA))
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = animatedPosition)
                        .fillMaxHeight(0.7f)
                        .width(100.dp)
                        .shadow(8.dp, shape = CircleShape)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFEEF5EE))
                )

                NavigationBar(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent,
                ) {
                    destinationList.forEachIndexed { index, dest ->
                        val isSelected = selectedIndex.value == index
                        val iconSize: Dp by animateDpAsState(
                            targetValue = 35.dp,
                            animationSpec = tween(500), label = ""
                        )

                        NavigationBarItem(
                            icon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isSelected) dest.iconFilled else dest.iconOut,
                                        contentDescription = dest.title,
                                        modifier = Modifier.size(iconSize)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            text = dest.title,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            label = null,
                            selected = isSelected,
                            onClick = {
                                selectedIndex.value = index
                                onDestinationClicked(
                                    when (dest) {
                                        Destinations.Home -> Screen.Home.route
                                        Destinations.Settings -> Screen.Settings.route
                                        Destinations.History -> Screen.History.route
                                    }
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        onDestinationClicked = { }
    )
}
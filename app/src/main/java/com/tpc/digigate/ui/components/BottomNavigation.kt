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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

enum class Destinations(val title: String, val iconFilled: ImageVector, val iconOut: ImageVector) {
    History("History", Icons.Filled.History, Icons.Outlined.History),
    Home("Home", Icons.Filled.Home, Icons.Outlined.Home),
    Profile("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

@Composable
fun BottomNavigationBar(
    selected: Destinations,
    onItemClick: (Destinations) -> Unit
) {
    val destinations = listOf(
        Destinations.History,
        Destinations.Home,
        Destinations.Profile
    )
    val selectedIndex = destinations.indexOf(selected) - 1

    Box(
        modifier = Modifier
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
                val itemWidth = this.maxWidth / destinations.size

                val animatedPosition by animateDpAsState(
                    targetValue = (selectedIndex * itemWidth.value).dp,
                    animationSpec = tween(durationMillis = 200)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFB8CECA))
                        .align(Alignment.Center)
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
                    containerColor = Color.Transparent
                ) {
                    destinations.forEachIndexed { index, destination ->
                        val isSelected = index - 1 == selectedIndex
                        val iconSize: Dp by animateDpAsState(
                            targetValue = if (isSelected) 35.dp else 35.dp,
                            animationSpec = tween(durationMillis = 500)
                        )

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { onItemClick(destination) },
                            icon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) destination.iconFilled else destination.iconOut,
                                        contentDescription = destination.title,
                                        modifier = Modifier.size(iconSize)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            text = destination.title,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    var selected by remember { mutableStateOf(Destinations.Home) }

    Surface {
        BottomNavigationBar(
            selected = selected,
            onItemClick = { selected = it }
        )
    }
}


package com.tpc.digigate.ui.components

import android.content.res.Configuration
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
import androidx.compose.runtime.*
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
import com.tpc.digigate.ui.theme.DigiGateTheme

//enum class Destinations(val title: String, val iconFilled: ImageVector, val iconOut: ImageVector) {
//    History("History", Icons.Filled.History, Icons.Outlined.History),
//    Home("Home", Icons.Filled.Home, Icons.Outlined.Home),
//    Profile("Profile", Icons.Filled.Person, Icons.Outlined.Person)
//}

@Composable
fun BottomNavigationBar(
    selected: Screen,
    onItemClick: (Screen) -> Unit
) {
//    val destinations = listOf(
//        Destinations.History,
//        Destinations.Home,
//        Destinations.Profile
//    )
//    val selectedIndex = destinations.indexOf(selected) - 1
    val selectedIndex = Screen.items.indexOf(selected)

    val colorScheme = MaterialTheme.colorScheme

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
                val itemWidth = this.maxWidth / Screen.items.size

                val animatedPosition by animateDpAsState(
                    targetValue = (selectedIndex * itemWidth.value).dp,
                    animationSpec = tween(durationMillis = 200)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(colorScheme.surfaceVariant)
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
                        .background(colorScheme.surface)
                )

                NavigationBar(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent
                ) {
                    Screen.items.forEachIndexed { index, screen ->
                        val isSelected = index == selectedIndex
                        val iconSize: Dp by animateDpAsState(
                            targetValue = 35.dp,
                            animationSpec = tween(durationMillis = 500)
                        )

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { onItemClick(screen) },
                            icon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) screen.iconFilled else screen.iconOut,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(iconSize)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            text = screen.title,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationBarPreview() {
    var selected by remember {
        mutableStateOf<Screen>(Screen.Home)
    }

    DigiGateTheme {
        BottomNavigationBar(
            selected = selected,
            onItemClick = { selected = it }
        )
    }
}

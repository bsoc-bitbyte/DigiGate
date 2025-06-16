package com.tpc.digigate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.navigation.Screen
import com.tpc.digigate.ui.theme.DigiGateTheme


@Composable
fun BottomNavigationBar(
    selected: Screen,
    onItemClick: (Screen) -> Unit
) {

    val destinationList = listOf<Screen>(
        Screen.History,
        Screen.Home,
        Screen.Profile
    )

    var selectedIndex = destinationList.indexOf(selected)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.navigationBarsPadding()
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(60.dp)
            ) {

                val itemWidth = this.maxWidth / destinationList.size

                val animatedOffset by animateDpAsState(
                    targetValue = ((selectedIndex - 1) * (itemWidth.value + 2)).dp,
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
                        .offset(x = animatedOffset)
                        .align(Alignment.Center)
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
                    destinationList.forEachIndexed { index, screen ->

                        val isSelected = index == selectedIndex

                        val iconSize: Dp by animateDpAsState(
                            targetValue = 35.dp,
                            animationSpec = tween(durationMillis = 500)
                        )

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { onItemClick(screen) },
                            icon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isSelected) screen.iconFilled else screen.iconOut,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(iconSize)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = screen.title,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            label = null,
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


@Preview(showBackground = true)
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

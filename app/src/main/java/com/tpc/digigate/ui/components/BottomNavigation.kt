package com.tpc.digigate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.navigation.Screen
import com.tpc.digigate.ui.theme.DigiGateTheme

val destinationList = listOf<Screen>(
    Screen.History,
    Screen.Home,
    Screen.Profile
)

@Composable
fun BottomNavigationBar(
    selected: Screen,
    onItemClick: (Screen) -> Unit
) {
    var selectedIndex = destinationList.indexOf(selected)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp + WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding()
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.navigationBarsPadding()
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(0.96f)
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
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB8CECA))
                        .align(Alignment.Center)
                )

                Box(
                    modifier = Modifier
                        .offset(x = animatedOffset)
                        .align(Alignment.Center)
                        .fillMaxHeight(0.7f)
                        .width(108.dp)
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

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { onItemClick(screen) },
                            icon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isSelected) screen.iconFilled else screen.iconOut,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(32.dp)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(modifier = Modifier.width(40.dp))
                                        Text(
                                            text = screen.title,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                        )
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            label = null,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
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


@Preview(showBackground = true, name = "Light Mode")
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

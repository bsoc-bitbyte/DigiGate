package com.tpc.digigate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.navigation.Screen
import com.tpc.digigate.ui.theme.DigiGateTheme

data class Item(
    val title: String,
    val iconFilled: ImageVector,
    val iconOut: ImageVector,
    val screen: Screen
)

val destinationList = listOf<Item>(
    Item("History", Icons.Filled.History, Icons.Outlined.History, Screen.History),
    Item("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.Home),
    Item("Profile", Icons.Filled.Person, Icons.Outlined.Person, Screen.Profile)
)

@Composable
fun BottomNavigationBar(
    selected: Screen,
    onItemClick: (Screen) -> Unit
) {
    var selectedIndex = 0
    destinationList.forEachIndexed { index, item ->
        if (selected == item.screen) selectedIndex = index
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp
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
                    .height(50.dp)
            ) {

                val itemWidth = this.maxWidth / destinationList.size

                val animatedOffset by animateDpAsState(
                    targetValue = ((selectedIndex - 1) * (itemWidth.value + 2)).dp,
                    animationSpec = tween()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.Center)
                )

                Box(
                    modifier = Modifier
                        .offset(x = animatedOffset)
                        .align(Alignment.Center)
                        .height(36.dp)
                        .width(108.dp)
                        .shadow(8.dp, shape = CircleShape)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.background)
                )

                NavigationBar(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent
                ) {
                    destinationList.forEachIndexed { index, screen ->

                        val isSelected = index == selectedIndex

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { },
                            icon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable(
                                        enabled = true,
                                        onClick = { onItemClick(screen.screen) },
                                        interactionSource = null,
                                        indication = null
                                    )
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) screen.iconFilled else screen.iconOut!!,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    AnimatedVisibility(visible = isSelected) {
                                        Spacer(modifier = Modifier.width(40.dp))
                                        CompositionLocalProvider(LocalDensity provides LocalDensity.current.run {
                                            Density(
                                                density,
                                                fontScale = 1f
                                            )
                                        }) {
                                            Text(
                                                text = screen.title,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 4.dp)
                                            )
                                        }
                                    }
                                }
                            },
                            alwaysShowLabel = false,
                            label = null,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                disabledIconColor = Color.Transparent,
                                disabledTextColor = Color.Transparent,
                                selectedIconColor = if (MaterialTheme.colorScheme.background == Color.White) Color.Black else Color.White
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

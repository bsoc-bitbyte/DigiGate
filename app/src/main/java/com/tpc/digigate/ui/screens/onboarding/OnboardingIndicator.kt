package com.tpc.digigate.ui.screens.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.BlackButton
import com.tpc.digigate.ui.theme.MutedText

@Composable
fun IndicatorUi(
    pageSize: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = BlackButton,
    inactiveColor: Color = MutedText.copy(alpha = 0.3f),
    dotSize: Dp = 8.dp,
    activeWidth: Dp = 24.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageSize) { index ->
            val isSelected = index == currentPage
            val width by animateDpAsState(
                targetValue = if (isSelected) activeWidth else dotSize,
                label = "indicatorWidth"
            )
            val color by animateColorAsState(
                targetValue = if (isSelected) activeColor else inactiveColor,
                label = "indicatorColor"
            )

            Box(
                modifier = Modifier
                    .height(dotSize)
                    .width(width)
                    .clip(CircleShape)
                    .background(color)
            )

            if (index < pageSize - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}

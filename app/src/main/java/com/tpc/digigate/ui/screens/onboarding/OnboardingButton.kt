package com.tpc.digigate.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.theme.PrimaryText
import com.tpc.digigate.ui.theme.SageLight

@Composable
fun ButtonUi(
    text: String = "Next",
    backgroundColor: Color = SageLight,
    textColor: Color = PrimaryText,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 16,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(52.dp)
    ) {
        Text(text = text, fontSize = fontSize.sp, style = textStyle)
    }
}

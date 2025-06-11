package com.tpc.digigate.ui.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tpc.digigate.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Afacad = FontFamily(
    Font(R.font.afacad_regular, FontWeight.Normal)
)

val LogoText = TextStyle(
    fontFamily = Afacad,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp
)

val Typography = Typography().withFontFamily(Inter)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun Typography.withFontFamily(fontFamily: FontFamily): Typography {
    return Typography(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily, fontSize = 20.sp),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily, fontSize = 18.sp),
        bodySmall = bodySmall.copy(fontFamily = fontFamily, fontSize = 16.sp),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily),
        displayLargeEmphasized = displayLargeEmphasized.copy(fontFamily = fontFamily),
        displayMediumEmphasized = displayMediumEmphasized.copy(fontFamily = fontFamily),
        displaySmallEmphasized =displaySmallEmphasized.copy(fontFamily = fontFamily),
        headlineLargeEmphasized = headlineLargeEmphasized.copy(fontFamily = fontFamily),
        headlineMediumEmphasized = headlineMediumEmphasized.copy(fontFamily = fontFamily),
        headlineSmallEmphasized = headlineSmallEmphasized.copy(fontFamily = fontFamily),
        titleLargeEmphasized = titleLargeEmphasized.copy(fontFamily = fontFamily),
        titleMediumEmphasized =titleMediumEmphasized.copy(fontFamily = fontFamily),
        titleSmallEmphasized = titleSmallEmphasized.copy(fontFamily = fontFamily),
        bodyLargeEmphasized = bodyLargeEmphasized.copy(fontFamily = fontFamily, fontSize = 20.sp),
        bodyMediumEmphasized = bodyMediumEmphasized.copy(fontFamily = fontFamily, fontSize = 18.sp),
        bodySmallEmphasized = bodySmallEmphasized.copy(fontFamily = fontFamily, fontSize = 16.sp),
        labelLargeEmphasized = labelLargeEmphasized.copy(fontFamily = fontFamily),
        labelMediumEmphasized = labelMediumEmphasized.copy(fontFamily = fontFamily),
        labelSmallEmphasized = labelSmallEmphasized.copy(fontFamily = fontFamily)
    )
}

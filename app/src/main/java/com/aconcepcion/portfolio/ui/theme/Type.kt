package com.aconcepcion.portfolio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aconcepcion.portfolio.R

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_bold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 64.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Black,
        fontSize = 48.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.asap)),
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
    )
)
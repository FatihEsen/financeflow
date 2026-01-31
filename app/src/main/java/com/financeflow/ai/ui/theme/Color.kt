package com.financeflow.ai.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val Slate950 = Color(0xFF020617)
val Slate900 = Color(0xFF0F172A)
val Slate800 = Color(0xFF1E293B)
val Indigo600 = Color(0xFF4F46E5)
val Indigo500 = Color(0xFF6366F1)
val Indigo400 = Color(0xFF818CF8)
val Teal400 = Color(0xFF2DD4BF)
val Rose400 = Color(0xFFFB7185)
val GlassWhite = Color(0x1AFFFFFF)
val GlassIndigo = Color(0x334F46E5)

val FinanceFlowColorScheme = darkColorScheme(
    primary = Indigo500,
    secondary = Teal400,
    tertiary = Rose400,
    background = Slate950,
    surface = Slate900,
    onPrimary = Color.White,
    onSecondary = Slate950,
    onBackground = Color.White,
    onSurface = Color.White,
    surfaceVariant = Slate800
)

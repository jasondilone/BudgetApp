package com.example.budgetapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimaryColor,
    secondary = darkSecondaryColor,
    tertiary = darkTertiaryColor,
    onPrimary = Color.White,
    background = darkBackgroundColor,
    onBackground = Color.White,
    onSecondary = Color.White,
    onSurfaceVariant = brightRed
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    onPrimary = Color.Black,
    background = Color.White,
    onSecondary = Color.White,
    onSurfaceVariant = brightRed

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BudgetAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
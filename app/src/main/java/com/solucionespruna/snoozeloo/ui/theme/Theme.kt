package com.solucionespruna.snoozeloo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = SnoozelooBlue,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onPrimary = SnoozelooWhite,
    background = SnoozelooGreyBackground,
    onBackground = SnoozelooBlack,

    surface = SnoozelooWhite,
    onSurface = SnoozelooGreyDark,

    // disabled
    surfaceVariant = SnoozelooGreyDisabled,
    onSurfaceVariant = SnoozelooWhite

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SnoozelooTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
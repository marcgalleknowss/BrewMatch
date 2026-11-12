package com.example.brewmatch.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BrewColorScheme = lightColorScheme(
    primary = BrewBrown,
    onPrimary = BrewWhite,
    secondary = BrewGray,
    onSecondary = BrewWhite,
    background = BrewWhite,
    onBackground = BrewBlack,
    surface = BrewWhite,
    onSurface = BrewBlack,
    outline = BrewBlack
)

@Composable
fun BrewMatchTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BrewColorScheme,
        typography = BrewTypography,
        content = content
    )
}
package com.example.oradore.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.oradore.color.Color

private val DarkColorPalette = darkColors(
    primary = Color.primary.hex.color,
    primaryVariant = Color.ternary.hex.color,
    secondary = Color.secondary.hex.color
)

private val LightColorPalette = lightColors(
    primary = Color.primary.hex.color,
    primaryVariant = Color.ternary.hex.color,
    secondary = Color.secondary.hex.color
)

@Composable
fun OradoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content,
    )
}

val String.color
    get() = androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor(this))
package com.example.playbox.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PlayBoxTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) darkColorScheme(
        primary = Color(0xFFB8A4FF), secondary = Color(0xFF7DDBC7),
    ) else lightColorScheme(primary = Color(0xFF6546B5), secondary = Color(0xFF006B5B))
    MaterialTheme(colorScheme = colors, content = content)
}

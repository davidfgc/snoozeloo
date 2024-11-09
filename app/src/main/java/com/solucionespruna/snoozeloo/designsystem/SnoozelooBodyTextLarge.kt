package com.solucionespruna.snoozeloo.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SnoozelooBodyTextLarge(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun SnoozelooBodyTextMedium(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.bodyMedium,
    )
}
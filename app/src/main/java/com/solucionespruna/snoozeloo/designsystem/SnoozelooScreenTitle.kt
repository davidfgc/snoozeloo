package com.solucionespruna.snoozeloo.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnoozelooScreenTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier.padding(16.dp),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineSmall,
    )
}
package com.solucionespruna.snoozeloo.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooIconButton(vector: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier
            .clip(RoundedCornerShape(8.dp))
            .size(32.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            imageVector = vector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun SnoozelooIconButtonPreview() {
    SnoozelooTheme {
        Surface {
            Box(Modifier.background(Color.Red)) {
                SnoozelooIconButton(Icons.Default.Close) {}
            }
        }
    }
}
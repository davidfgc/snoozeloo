package com.solucionespruna.snoozeloo.designsystem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 32.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier.height(height),
        enabled,
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(text, fontWeight = FontWeight.SemiBold, style = textStyle)
    }
}

@Preview
@Composable
private fun SnoozelooButtonPreview() {
    SnoozelooTheme {
        Surface {
            SnoozelooButton("Save") {}
        }
    }
}

@Preview
@Composable
private fun SnoozelooButtonDisabledPreview() {
    SnoozelooTheme {
        Surface {
            SnoozelooButton("Save", enabled = false) {}
        }
    }
}
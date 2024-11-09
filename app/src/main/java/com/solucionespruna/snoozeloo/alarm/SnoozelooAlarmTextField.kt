package com.solucionespruna.snoozeloo.alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SnoozelooAlarmTextField(timeFieldState: TextFieldState) {
    val fieldLength = 4

    BasicTextField(
        timeFieldState,
        inputTransformation = InputTransformation
            .then(DigitsOnlyTransformation())
            .then(InputTransformation.maxLength(fieldLength)),
        textStyle = TextStyle.Default.copy(color = Color.Transparent),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorator = { innerTextField ->
            var value = timeFieldState.text
            while (value.length < fieldLength) {
                value = "0$value"
            }
            Box {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AlarmDetailTextBox {
                        AlarmDetailText(value.substring(IntRange(0, 1)))
                    }
                    Box(
                        modifier = Modifier.width(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AlarmDetailText(text = ":")
                    }
                    AlarmDetailTextBox {
                        AlarmDetailText(value.substring(IntRange(2, 3)))
                    }
                }
            }
        }
    )
}

@Composable
fun RowScope.AlarmDetailTextBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)
        .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun AlarmDetailText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.displayLarge,
    )
}
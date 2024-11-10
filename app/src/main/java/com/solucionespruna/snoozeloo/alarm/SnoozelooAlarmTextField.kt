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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SnoozelooAlarmTextField(timeFieldState: TextFieldState, modifier: Modifier = Modifier) {
    val fieldLength = 4

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        timeFieldState,
        modifier.focusRequester(focusRequester),
        inputTransformation = InputTransformation
            .then(DigitsOnlyTransformation())
            .then(InputTransformation.maxLength(fieldLength)),
        textStyle = TextStyle.Default.copy(color = Color.Transparent),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorator = {
            var value = timeFieldState.text
            while (value.length < fieldLength) {
                value = "0$value"
            }
            val color =
                if (value == "0000") MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.primary
            Box {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AlarmDetailTextBox {
                        AlarmDetailText(value.substring(IntRange(0, 1)), color)
                    }
                    Box(
                        modifier = Modifier.width(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AlarmDetailText(text = ":")
                    }
                    AlarmDetailTextBox {
                        AlarmDetailText(value.substring(IntRange(2, 3)), color)
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
private fun AlarmDetailText(text: String, color: Color = MaterialTheme.colorScheme.onSurface) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.displayLarge,
    )
}
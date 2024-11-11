package com.solucionespruna.snoozeloo.alarm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextLarge
import com.solucionespruna.snoozeloo.designsystem.SnoozelooButton
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmNameDialog(
    nameFieldState: TextFieldState,
    onAction: (action: AlarmAction) -> Unit
) {
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
            .padding(16.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Card {
            Column(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SnoozelooBodyTextLarge(text = stringResource(R.string.alarm_detail__alarm_name))
                BasicTextField(
                    state = nameFieldState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(vertical = 10.dp, horizontal = 12.dp)
                        .focusRequester(focusRequester),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    lineLimits = TextFieldLineLimits.SingleLine
                )
                SnoozelooButton(text = stringResource(R.string.common__save), Modifier.align(Alignment.End)) {
                    onAction(AlarmAction.Dismiss)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AlarmNameDialogPreview() {
    SnoozelooTheme {
        AlarmNameDialog(nameFieldState = TextFieldState()) {}
    }
}
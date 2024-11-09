package com.solucionespruna.snoozeloo.alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextLarge
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextMedium
import com.solucionespruna.snoozeloo.designsystem.SnoozelooButton
import com.solucionespruna.snoozeloo.designsystem.SnoozelooIconButton
import com.solucionespruna.snoozeloo.designsystem.SnoozelooTopBar
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmDetailScreen(onClose: () -> Unit) {
    val timeFieldState by remember {
        mutableStateOf(TextFieldState("10:20"))
    }
    val nameFieldState by remember {
        mutableStateOf(TextFieldState("work"))
    }

    AlarmDetailScaffold(timeFieldState, nameFieldState) {
        when(it) {
            is AlarmAction.Close -> onClose()
            else -> TODO("not implemented")
        }
    }
}

@Composable
private fun AlarmDetailScaffold(
    timeFieldState: TextFieldState,
    nameFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    onAction: (action: AlarmAction) -> Unit,
) {
    Scaffold(
        topBar = {
            SnoozelooTopBar(
                startIcon = {
                    SnoozelooIconButton(vector = Icons.Default.Close) {
                        onAction(AlarmAction.Close)
                    }
                },
                endIcon = {
                    SnoozelooButton(text = "Save", enabled = false) {
                        onAction(AlarmAction.Save)
                    }
                }
            )
        }
    ) { innerPadding ->
        AlarmDetailContent(
            timeFieldState,
            nameFieldState,
            modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun AlarmDetailContent(
    timeFieldState: TextFieldState,
    nameFieldState: TextFieldState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(onClick = { /*TODO*/ }) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
            ) {
                BasicTextField(
                    timeFieldState,
                    decorator = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AlarmDetailTextBox {
                                AlarmDetailText(timeFieldState.text.substring(IntRange(0, 1)))
                            }
                            Box(
                                modifier = Modifier.width(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                AlarmDetailText(text = ":")
                            }
                            AlarmDetailTextBox {
                                AlarmDetailText(timeFieldState.text.substring(IntRange(3, 4)))
                            }
                        }
                    }
                )
            }
        }
        Card(onClick = { /*TODO*/ }) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SnoozelooBodyTextLarge(text = stringResource(R.string.alarm_detail__alarm_name))
                SnoozelooBodyTextMedium(text = nameFieldState.text.toString())
            }
        }
    }
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

@Preview
@Composable
private fun AlarmDetailPreview() {
    val timeFieldState by remember {
        mutableStateOf(TextFieldState("10:20"))
    }
    val nameFieldState by remember {
        mutableStateOf(TextFieldState("10:20"))
    }

    SnoozelooTheme {
        Surface {
            AlarmDetailScaffold(timeFieldState, nameFieldState) {}
        }
    }
}
package com.solucionespruna.snoozeloo.alarm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextLarge
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextMedium
import com.solucionespruna.snoozeloo.designsystem.SnoozelooButton
import com.solucionespruna.snoozeloo.designsystem.SnoozelooIconButton
import com.solucionespruna.snoozeloo.designsystem.SnoozelooTopBar
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlarmDetailScreen(
    viewModel: AlarmDetailViewModel = koinViewModel(),
    onClose: () -> Unit
) {
    var isNameDialogOpened by remember {
        mutableStateOf(false)
    }
    val state by viewModel.alarmDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        when(state) {
            AlarmDetailUiState.AlarmSaved -> onClose()
            else -> {}
        }
    }
    when (state) {
        AlarmDetailUiState.Idle -> {
            AlarmDetailScreen(viewModel.alarmDetailState, isNameDialogOpened) {
                when(it) {
                    AlarmAction.Close -> onClose()
                    AlarmAction.Dismiss -> isNameDialogOpened = false
                    AlarmAction.NameClicked -> isNameDialogOpened = true
                    AlarmAction.Save -> viewModel.save()
                }
            }
        }
        AlarmDetailUiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...")
            }
        }
        AlarmDetailUiState.AlarmSaved -> {}
    }
}

@Composable
private fun AlarmDetailScreen(
    alarmDetailState: AlarmDetailState,
    isNameDialogOpened: Boolean,
    onAction: (AlarmAction) -> Unit
) {
    AlarmDetailScaffold(alarmDetailState, isNameDialogOpened) {
        onAction(it)
    }
}

@Composable
private fun AlarmDetailScaffold(
    alarmDetailState: AlarmDetailState,
    isNameDialogOpened: Boolean,
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
                    SnoozelooButton(
                        text = stringResource(id = R.string.common__save),
                        enabled = alarmDetailState.isTimeValid
                    ) {
                        onAction(AlarmAction.Save)
                    }
                }
            )
        }
    ) { innerPadding ->
        AlarmDetailContent(
            alarmDetailState,
            modifier.padding(innerPadding),
            onAction,
        )
        if (isNameDialogOpened) {
            AlarmNameDialog(alarmDetailState.nameState, onAction)
        }
    }
}

@Composable
private fun AlarmDetailContent(
    alarmDetailState: AlarmDetailState,
    modifier: Modifier = Modifier,
    onAction: (AlarmAction) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(onClick = { /*TODO*/ }) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
            ) {
                SnoozelooAlarmTextField(alarmDetailState.timeState)
            }
        }
        Card(onClick = { onAction(AlarmAction.NameClicked) }) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SnoozelooBodyTextLarge(text = stringResource(R.string.alarm_detail__alarm_name))
                SnoozelooBodyTextMedium(text = alarmDetailState.nameState.text.toString())
            }
        }
    }
}

@Preview
@Composable
private fun AlarmDetailPreview() {
    SnoozelooTheme {
        Surface {
            AlarmDetailScaffold(AlarmDetailState(), false) {}
        }
    }
}
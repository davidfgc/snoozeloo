package com.solucionespruna.snoozeloo.alarms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.designsystem.SnoozelooFab
import com.solucionespruna.snoozeloo.designsystem.SnoozelooScreenTitle
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@Composable
fun AlarmsScreen(
    modifier: Modifier = Modifier,
    alarmsViewModel: AlarmsViewModel = koinViewModel(),
    onAddClicked: () -> Unit
) {
    val alarms by alarmsViewModel.alarms.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            alarmsViewModel.initialize()
        }
    }

    AlarmsScreen(alarms, modifier) {
        when (it) {
            AlarmsAction.AddAlarm -> onAddClicked()
            is AlarmsAction.UpdateAlarm -> alarmsViewModel.updateAlarm(it.alarm)
        }
    }
}

@Composable
private fun AlarmsScreen(
    alarms: List<Alarm>,
    modifier: Modifier = Modifier,
    onAction: (AlarmsAction) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            SnoozelooFab {
                onAction(AlarmsAction.AddAlarm)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            SnoozelooScreenTitle(title = "Your Alarms")
            when {
                alarms.isNotEmpty() -> AlarmsList(alarms) {
                    onAction(AlarmsAction.UpdateAlarm(it))
                }
                else -> EmptyState()
            }
        }
    }
}

@Composable
fun AlarmsList(
    alarms: List<Alarm>,
    modifier: Modifier = Modifier,
    onAlarmEnabledChange: (Alarm) -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        alarms.forEach { alarm ->
            key(alarm.id) {
                AlarmItem(alarm) {
                    onAlarmEnabledChange(alarm.copy(enabled = it))
                }
            }
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_alarm),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            stringResource(id = R.string.alarms_empty_state_text),
            modifier = Modifier.padding(32.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun AlarmsScreenPreview() {
    SnoozelooTheme {
        Surface {
            AlarmsScreen(emptyList()) {}
        }
    }
}

@Preview
@Composable
fun AlarmListPreview(modifier: Modifier = Modifier) {
    val alarm = Alarm(
        name = "Wake Up",
        date = Calendar.getInstance().timeInMillis + 90 * 60 * 1000,
        enabled = true
    )
    SnoozelooTheme {
        AlarmsList(listOf(alarm)) {}
    }
}
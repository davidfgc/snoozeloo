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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.designsystem.SnoozelooFab
import com.solucionespruna.snoozeloo.designsystem.SnoozelooScreenTitle
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmsScreen(modifier: Modifier = Modifier, onAddClicked: () -> Unit) {
    val alarms by remember {
        mutableStateOf(emptyList<Alarm>())
    }
    Scaffold(
        floatingActionButton = {
            SnoozelooFab {
                onAddClicked()
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            SnoozelooScreenTitle(title = "Your Alarms")
            when {
                alarms.isEmpty() -> EmptyState()
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
            AlarmsScreen {}
        }
    }
}
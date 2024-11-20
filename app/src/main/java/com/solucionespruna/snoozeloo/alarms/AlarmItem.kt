package com.solucionespruna.snoozeloo.alarms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.boundaries.date.SnoozelooDateTime
import com.solucionespruna.snoozeloo.designsystem.SnoozelooBodyTextLarge
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmItem(
    alarm: Alarm,
    modifier: Modifier = Modifier,
    onEnabledChange: (Boolean) -> Unit,
) {
    val nextOccurrenceText = alarm.nextOccurrenceText()

    Card {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)) {
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SnoozelooBodyTextLarge(alarm.name, fontWeight = FontWeight.SemiBold)
                Text(
                    text = SnoozelooDateTime.formatTime(alarm.date),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.displayMedium,
                )
                if (nextOccurrenceText.isNotBlank())
                    Text("Alarm in $nextOccurrenceText")
            }
            Switch(alarm.enabled, onCheckedChange = onEnabledChange)
        }
    }
}

@Preview
@Composable
private fun AlarmItemPreview() {
    val alarm = Alarm(
        name = "Wake Up",
        date = SnoozelooDateTime.nowInMillis() + 90 * 60 * 1000,
        enabled = true
    )
    SnoozelooTheme {
        AlarmItem(alarm) {}
    }
}

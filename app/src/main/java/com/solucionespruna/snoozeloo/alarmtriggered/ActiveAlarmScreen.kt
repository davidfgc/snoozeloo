package com.solucionespruna.snoozeloo.alarmtriggered

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.solucionespruna.snoozeloo.R
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.designsystem.SnoozelooButton
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme
import java.util.Calendar
import java.util.Locale

@Composable
fun ActiveAlarmScreen() {
    val alarm = Alarm(
        name = "Work",
        date = Calendar.getInstance().timeInMillis,
        enabled = true,
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                contentDescription = null,
                Modifier.size(62.dp)
            )
            Text(
                text = alarm.formatTime(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = TextUnit(82f, TextUnitType.Sp),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = alarm.name.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            SnoozelooButton(
                text = stringResource(R.string.common__turn_off),
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.headlineSmall,
                height = 45.dp
            ) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActiveAlarmScreenPreview() {
    SnoozelooTheme {
        ActiveAlarmScreen()
    }
}
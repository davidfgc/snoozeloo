package com.solucionespruna.snoozeloo.alarms

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AlarmsScreen(modifier: Modifier = Modifier, onAlarmDetailClicked: () -> Unit) {
    Text(text = "Alarms Screen")
    Button(onClick = onAlarmDetailClicked) {
        Text(text = "Go to detail")
    }
}
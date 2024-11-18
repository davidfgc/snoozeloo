package com.solucionespruna.snoozeloo.alarms

import com.solucionespruna.snoozeloo.alarm.Alarm

sealed interface AlarmsAction {
    data object AddAlarm: AlarmsAction
    data class UpdateAlarm(val alarm: Alarm): AlarmsAction
}
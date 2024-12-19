package com.solucionespruna.snoozeloo.alarm

import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import com.solucionespruna.snoozeloo.alarmtriggered.SnoozelooScheduler

class SaveAlarmUseCase(
    private val alarmRepository: AlarmRepository,
    private val snoozelooScheduler: SnoozelooScheduler,
) {
    suspend fun execute(alarm: Alarm) {
        val id = alarmRepository.saveAlarm(alarm)
        val alarmSaved = alarm.copy(
            id = id
        )
        if (alarm.enabled)
            snoozelooScheduler.schedule(alarmSaved)
        else
            snoozelooScheduler.cancel(alarmSaved)
    }
}
package com.solucionespruna.snoozeloo.alarm

import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import com.solucionespruna.snoozeloo.alarmtriggered.SnoozelooScheduler

class SaveAlarmUseCase(
    private val alarmRepository: AlarmRepository,
    private val snoozelooScheduler: SnoozelooScheduler,
) {
    suspend fun execute(alarm: Alarm) {
        alarmRepository.saveAlarm(alarm)
        if (alarm.enabled)
            snoozelooScheduler.schedule(alarm)
        else
            snoozelooScheduler.cancel(alarm)
    }
}
package com.solucionespruna.snoozeloo.alarm.data

import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.database.alarm.AlarmDao
import com.solucionespruna.snoozeloo.database.alarm.AlarmMapper

interface AlarmRepository {
    fun getAlarms(): List<Alarm>
    suspend fun saveAlarm(alarm: Alarm): Long
}

class AlarmRepositoryImpl(
    private val alarmDao: AlarmDao
): AlarmRepository {

    override suspend fun saveAlarm(alarm: Alarm) =
        alarmDao.updateOrInsert(AlarmMapper.toAlarmEntity(alarm))

    override fun getAlarms(): List<Alarm> {
        return alarmDao.getAlarms().map {
            Alarm(
                it.id,
                it.name,
                it.date,
                it.enabled,
            )
        }
    }

}
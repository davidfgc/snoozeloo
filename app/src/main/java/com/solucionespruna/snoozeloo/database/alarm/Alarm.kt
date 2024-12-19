package com.solucionespruna.snoozeloo.database.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val name: String,
    val enabled: Boolean,
)

class AlarmMapper {
    companion object {
        fun toAlarmEntity(alarm: com.solucionespruna.snoozeloo.alarm.Alarm): Alarm {
            return Alarm(
                id = alarm.id,
                date = alarm.date,
                name = alarm.name,
                enabled = alarm.enabled,
            )
        }
    }
}

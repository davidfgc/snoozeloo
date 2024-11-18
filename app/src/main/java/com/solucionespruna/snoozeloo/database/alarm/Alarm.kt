package com.solucionespruna.snoozeloo.database.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(
    @PrimaryKey
    val id: Int = 0,
    val date: Long,
    val name: String
)

class AlarmMapper {
    companion object {
        fun toAlarmEntity(alarm: com.solucionespruna.snoozeloo.alarm.Alarm): Alarm {
            return Alarm(
                date = alarm.date,
                name = alarm.name
            )
        }
    }
}

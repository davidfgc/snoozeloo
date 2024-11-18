package com.solucionespruna.snoozeloo.database.alarm

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Alarm::class],
    version = 1
)
abstract class AlarmDatabase: RoomDatabase() {
    abstract val dao: AlarmDao
}
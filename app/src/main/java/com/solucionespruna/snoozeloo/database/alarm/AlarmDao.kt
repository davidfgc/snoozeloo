package com.solucionespruna.snoozeloo.database.alarm

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AlarmDao {

    @Upsert
    suspend fun updateOrInsert(alarm: Alarm)

    @Query("SELECT * FROM alarm ORDER BY date ASC")
    fun getAlarms(): List<Alarm>

}
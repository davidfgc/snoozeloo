package com.solucionespruna.snoozeloo.alarm

import com.solucionespruna.snoozeloo.boundaries.date.SnoozelooDateTime
import com.solucionespruna.snoozeloo.boundaries.date.SnoozelooDateTime.nowInMillis
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.math.ceil
import kotlin.math.floor

data class Alarm(
    val id: Int = 0,
    val name: String,
    val date: Long,
    val enabled: Boolean
) {
    fun nextOccurrenceText(): String {
        val millisToNextOccurrence = date - nowInMillis()
        return if (millisToNextOccurrence <= 0) ""
        else {
            val minInMillis = 1000f * 60
            val hourInMillis = minInMillis * 60
            val hours = floor(millisToNextOccurrence / hourInMillis.toDouble()).toInt()
            val min = (ceil(millisToNextOccurrence % hourInMillis.toDouble()) / minInMillis).toInt()
            val res = StringBuilder()

            if (hours > 0) res.append("${hours}h ")
            res.append("${min}min")
            res.toString()
        }
    }

    companion object {
        fun getNextAlarmOccurrenceMillis(dateString: String): Long {
            val formatter = DecimalFormat("0000")
            val dateFormatted = formatter.format(dateString.trim().toInt())

            val hour = dateFormatted.substring(0, 2).toInt()
            val minute = dateFormatted.substring(2, 4).toInt()

            val localDateTime = LocalDateTime.now(ZoneId.systemDefault())
                .withHour(hour)
                .withMinute(minute)
                .withSecond(0)

            return if (localDateTime.isBefore(LocalDateTime.now()))
                localDateTime
                    .withDayOfMonth(localDateTime.dayOfMonth + 1)
                    .toEpochSecond(SnoozelooDateTime.zoneOffset) * 1000
            else
                localDateTime.toEpochSecond(SnoozelooDateTime.zoneOffset) * 1000
        }
    }
}


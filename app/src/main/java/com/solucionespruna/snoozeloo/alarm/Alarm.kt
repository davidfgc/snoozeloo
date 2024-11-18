package com.solucionespruna.snoozeloo.alarm

import java.text.DecimalFormat
import java.util.Calendar
import kotlin.math.ceil
import kotlin.math.floor

data class Alarm(
    val id: Int = 0,
    val name: String,
    val date: Long,
    val enabled: Boolean
) {
    fun formatTime(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val formatter = DecimalFormat("00")
        val hour = formatter.format(calendar.get(Calendar.HOUR_OF_DAY))
        val min = formatter.format(calendar.get(Calendar.MINUTE))
        return "${hour}:${min}"
    }

    fun nextOccurrenceText(): String {
        val millisToNextOccurrence = timeToNextOccurrence()
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

    private fun timeToNextOccurrence(): Long {
        val currentMillis = Calendar.getInstance().timeInMillis
        return date - currentMillis
    }
}


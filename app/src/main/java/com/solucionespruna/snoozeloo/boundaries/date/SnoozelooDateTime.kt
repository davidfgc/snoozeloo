package com.solucionespruna.snoozeloo.boundaries.date

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object SnoozelooDateTime {
    val zoneOffset: ZoneOffset = ZoneOffset.ofHours(-5)

    fun nowInMillis() = LocalDateTime.now().toEpochSecond(zoneOffset) * 1000

    fun formatTime(millis: Long, format: String = "HH:mm"): String {
        val localDateTime = fromMillis(millis)
        return localDateTime.format(DateTimeFormatter.ofPattern(format))
    }

    fun fromMillis(millis: Long): LocalDateTime =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())

    fun millisTo(targetMillis: Long): Long {
        return targetMillis - nowInMillis()
    }
}
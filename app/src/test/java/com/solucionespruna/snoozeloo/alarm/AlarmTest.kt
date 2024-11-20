package com.solucionespruna.snoozeloo.alarm

import com.solucionespruna.snoozeloo.boundaries.date.SnoozelooDateTime
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class AlarmTest {

    private val oneMinuteMillis = 60 * 1000
    private val oneHourMillis = oneMinuteMillis * 60
    private val zoneOffset = ZoneOffset.ofHours(-5)

    @Test
    fun `nextOccurrenceText show 30m`() {
        val currentTime = SnoozelooDateTime.nowInMillis()
        val millisToNextOccurrence = 30 * oneMinuteMillis
        val alarm = Alarm(
            name = "name",
            date = currentTime + millisToNextOccurrence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "30min") { "current: $text" }
    }

    @Test
    fun `nextOccurrenceText with 90min show 1h 30m`() {
        val currentTime = SnoozelooDateTime.nowInMillis()
        val millisToNextOccurrence = 1 * oneHourMillis + 30 * oneMinuteMillis
        val alarm = Alarm(
            name = "name",
            date = currentTime + millisToNextOccurrence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "1h 30min") { "current: $text" }
    }

    @Test
    fun `nextOccurrenceText with 90min and 10s show 1h 30m`() {
        val currentTime = SnoozelooDateTime.nowInMillis()
        val millisToNextOccurence = 1 * oneHourMillis + 30 * oneMinuteMillis + 10000
        val alarm = Alarm(
            name = "name",
            date = currentTime + millisToNextOccurence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "1h 30min") { "current: $text" }
    }

    @Test
    fun `formatTime with 8am expects 08-00`() {
        val millis = LocalDateTime.now()
            .withHour(8)
            .withMinute(0).toEpochSecond(ZoneOffset.ofHours(-5)) * 1000
        val alarm = Alarm(
            name = "name",
            date = millis,
            enabled = true
        )

        val res = alarm.formatTime()

        assert(res == "08:00") { "current: $res" }
    }

    @Test
    fun `formatTime with 8pm expects 20-00`() {
        val millis = LocalDateTime.now()
            .withHour(20)
            .withMinute(0).toEpochSecond(zoneOffset) * 1000
        val alarm = Alarm(
            name = "name",
            date = millis,
            enabled = true
        )

        val res = alarm.formatTime()

        assert(res == "20:00") { "current: $res" }
    }

    @Test
    fun `dateFromStringToLong 0800 expects hour 8`() {
        val millis = dateFromStringToLong("0800")

        val localDateTime = SnoozelooDateTime.fromMillis(millis)
        assert(localDateTime.hour == 8) { "hour: ${localDateTime.hour}" }
    }

    @Test
    fun `dateFromStringToLong 0829 expects min 29`() {
        val millis = dateFromStringToLong("0829")

        val localDateTime = SnoozelooDateTime.fromMillis(millis)
        assert(localDateTime.minute == 29) { "min: ${localDateTime.minute}" }
    }

    @Test
    fun `dateFromStringToLong expects futureDate`() {
        val localDateTime = LocalDateTime.now()

        val millis = dateFromStringToLong("${localDateTime.hour - 1}${localDateTime.minute}")

        val newDate = SnoozelooDateTime.fromMillis(millis)
        assert(localDateTime.isBefore(newDate))
    }
}
package com.solucionespruna.snoozeloo.alarm

import org.junit.Test
import java.util.Calendar

class AlarmTest {

    private val oneMinuteMillis = 60 * 1000
    private val oneHourMillis = oneMinuteMillis * 60

    @Test
    fun `nextOccurrenceText show 30m`() {
        val currentTime = Calendar.getInstance().timeInMillis
        val millisToNextOccurrence = 30 * oneMinuteMillis
        val alarm = Alarm(
            "name",
            currentTime + millisToNextOccurrence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "30min") { "current: $text" }
    }

    @Test
    fun `nextOccurrenceText with 90min show 1h 30m`() {
        val currentTime = Calendar.getInstance().timeInMillis
        val millisToNextOccurence = 1 * oneHourMillis + 30 * oneMinuteMillis
        val alarm = Alarm(
            "name",
            currentTime + millisToNextOccurence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "1h 30min") { "current: $text" }
    }

    @Test
    fun `nextOccurrenceText with 90min and 10s show 1h 30m`() {
        val currentTime = Calendar.getInstance().timeInMillis
        val millisToNextOccurence = 1 * oneHourMillis + 30 * oneMinuteMillis + 10000
        val alarm = Alarm(
            "name",
            currentTime + millisToNextOccurence,
            enabled = true,
        )

        val text = alarm.nextOccurrenceText()

        assert(text == "1h 30min") { "current: $text" }
    }

}
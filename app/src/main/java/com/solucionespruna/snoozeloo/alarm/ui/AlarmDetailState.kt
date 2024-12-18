package com.solucionespruna.snoozeloo.alarm.ui

import androidx.compose.foundation.text.input.TextFieldState

data class AlarmDetailState(
    val timeState: TextFieldState = TextFieldState(),
    val isTimeValid: Boolean = false,
    val nameState: TextFieldState = TextFieldState(),
)

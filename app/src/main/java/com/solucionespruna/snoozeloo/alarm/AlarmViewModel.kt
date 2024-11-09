package com.solucionespruna.snoozeloo.alarm

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlarmViewModel: ViewModel() {

    private val _timeFieldState = MutableStateFlow(TextFieldState())
    val timeFieldState: StateFlow<TextFieldState>
        get() = _timeFieldState.asStateFlow()

    private val _nameFieldState = MutableStateFlow(TextFieldState())
    val nameFieldState: StateFlow<TextFieldState>
        get() = _nameFieldState.asStateFlow()

}
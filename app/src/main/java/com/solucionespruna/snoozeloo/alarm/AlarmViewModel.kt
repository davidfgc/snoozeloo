package com.solucionespruna.snoozeloo.alarm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AlarmViewModel: ViewModel() {
    var alarmDetailState by mutableStateOf(AlarmDetailState())
        private set

    init {
        snapshotFlow {
            alarmDetailState.nameState.text
        }.onEach {
            val isNameValid = it.trim().isNotBlank()
            alarmDetailState = alarmDetailState.copy(
                isNameValid = isNameValid,
                isAlarmValid = alarmDetailState.isTimeValid && isNameValid
            )
        }.launchIn(viewModelScope)

        snapshotFlow {
            alarmDetailState.timeState.text
        }.onEach {
            val isTimeValid = it != "0000" && it.isNotBlank()
            alarmDetailState = alarmDetailState.copy(
                isTimeValid = isTimeValid,
                isAlarmValid = isTimeValid && alarmDetailState.isNameValid
            )
        }.launchIn(viewModelScope)
    }

    fun save() {

    }
}
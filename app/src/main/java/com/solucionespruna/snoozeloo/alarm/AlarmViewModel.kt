package com.solucionespruna.snoozeloo.alarm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Calendar

class AlarmViewModel(
    private val alarmRepository: AlarmRepository
): ViewModel() {

    private val _alarmDetailUiState = MutableStateFlow<AlarmDetailUiState>(AlarmDetailUiState.Idle)
    val alarmDetailUiState: StateFlow<AlarmDetailUiState>
        get() = _alarmDetailUiState.asStateFlow()

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
        viewModelScope.launch {
            _alarmDetailUiState.emit(AlarmDetailUiState.Loading)
            alarmRepository.saveAlarm(
                Alarm(
                    name = alarmDetailState.nameState.text.toString(),
                    date = dateFromStringToLong(alarmDetailState.timeState.text.toString()),
                    enabled = true
                )
            )
            _alarmDetailUiState.emit(AlarmDetailUiState.AlarmSaved)
        }
    }
}

sealed interface AlarmDetailUiState {
    data object Idle: AlarmDetailUiState
    data object Loading: AlarmDetailUiState
    data object AlarmSaved: AlarmDetailUiState
}

fun dateFromStringToLong(dateString: String): Long {
    if (dateString.length != 4) throw IllegalArgumentException("Invalid date string")

    val hour = dateString.substring(0, 1).toInt()
    val min = dateString.substring(2, 3).toInt()
    val date = Calendar.getInstance()
    date.set(Calendar.HOUR, hour)
    date.set(Calendar.MINUTE, min)

    return date.timeInMillis
}
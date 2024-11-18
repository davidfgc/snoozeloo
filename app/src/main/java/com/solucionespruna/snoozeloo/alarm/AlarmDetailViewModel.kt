package com.solucionespruna.snoozeloo.alarm

import android.icu.number.NumberFormatter
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Calendar

class AlarmDetailViewModel(
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
    val formatter = DecimalFormat("0000")
    val dateFormatted = formatter.format(dateString.trim().toInt())

    val hour = dateFormatted.substring(0, 2).toInt()
    val min = dateFormatted.substring(2, 4).toInt()
    val date = Calendar.getInstance()
    date.set(Calendar.HOUR_OF_DAY, hour)
    date.set(Calendar.MINUTE, min)

    if (date.before(Calendar.getInstance()))
        date.add(Calendar.DAY_OF_MONTH, 1)

    return date.timeInMillis
}
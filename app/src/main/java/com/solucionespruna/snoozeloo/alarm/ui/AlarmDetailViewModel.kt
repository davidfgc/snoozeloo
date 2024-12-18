package com.solucionespruna.snoozeloo.alarm.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.alarm.Alarm.Companion.getNextAlarmOccurrenceMillis
import com.solucionespruna.snoozeloo.alarm.SaveAlarmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AlarmDetailViewModel(
    private val saveAlarmUseCase: SaveAlarmUseCase,
): ViewModel() {

    private val _alarmDetailUiState = MutableStateFlow<AlarmDetailUiState>(AlarmDetailUiState.Idle)
    val alarmDetailUiState: StateFlow<AlarmDetailUiState>
        get() = _alarmDetailUiState.asStateFlow()

    var alarmDetailState by mutableStateOf(AlarmDetailState())
        private set

    init {
        snapshotFlow {
            alarmDetailState.timeState.text
        }.onEach {
            val isTimeValid = it.isNotBlank()
            alarmDetailState = alarmDetailState.copy(
                isTimeValid = isTimeValid,
            )
        }.launchIn(viewModelScope)
    }

    fun save() {
        viewModelScope.launch {
            _alarmDetailUiState.emit(AlarmDetailUiState.Loading)
            val alarm = Alarm(
                name = alarmDetailState.nameState.text.toString(),
                date = getNextAlarmOccurrenceMillis(alarmDetailState.timeState.text.toString()),
                enabled = true
            )
            saveAlarmUseCase.execute(alarm)
            _alarmDetailUiState.emit(AlarmDetailUiState.AlarmSaved)
        }
    }
}

sealed interface AlarmDetailUiState {
    data object Idle: AlarmDetailUiState
    data object Loading: AlarmDetailUiState
    data object AlarmSaved: AlarmDetailUiState
}

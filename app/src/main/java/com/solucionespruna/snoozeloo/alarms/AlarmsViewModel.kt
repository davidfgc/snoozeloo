package com.solucionespruna.snoozeloo.alarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.snoozeloo.alarm.Alarm
import com.solucionespruna.snoozeloo.alarm.SaveAlarmUseCase
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlarmsViewModel(
    private val alarmRepository: AlarmRepository,
    private val saveAlarmUseCase: SaveAlarmUseCase,
): ViewModel() {

    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>>
        get() = _alarms.asStateFlow()

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            val alarms = alarmRepository.getAlarms()
            launch(Dispatchers.Main) {
                _alarms.value = alarms
            }
        }
    }

    fun updateAlarm(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            saveAlarmUseCase.execute(alarm)
            initialize()
        }
    }
}
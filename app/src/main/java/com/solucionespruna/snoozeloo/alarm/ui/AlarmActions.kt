package com.solucionespruna.snoozeloo.alarm.ui

sealed interface AlarmAction {
    data object Close: AlarmAction
    data object Save: AlarmAction
    data object NameClicked : AlarmAction
    data object Dismiss: AlarmAction
}
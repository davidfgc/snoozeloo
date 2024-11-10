package com.solucionespruna.snoozeloo.alarm

sealed interface AlarmAction {
    data object Close: AlarmAction
    data object Save: AlarmAction
    data object NameClicked : AlarmAction
    data object Dismiss: AlarmAction
}
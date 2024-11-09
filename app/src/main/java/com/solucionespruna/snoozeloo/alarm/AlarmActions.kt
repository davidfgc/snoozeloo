package com.solucionespruna.snoozeloo.alarm

sealed interface AlarmAction {
    object Close: AlarmAction
    object Save: AlarmAction
}
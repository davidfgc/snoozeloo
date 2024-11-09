package com.solucionespruna.snoozeloo.di

import com.solucionespruna.snoozeloo.alarm.AlarmViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AlarmViewModel)
}
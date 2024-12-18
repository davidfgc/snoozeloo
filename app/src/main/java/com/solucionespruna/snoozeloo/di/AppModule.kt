package com.solucionespruna.snoozeloo.di

import androidx.room.Room
import com.solucionespruna.snoozeloo.alarm.AlarmDetailViewModel
import com.solucionespruna.snoozeloo.alarms.AlarmsViewModel
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepository
import com.solucionespruna.snoozeloo.alarm.data.AlarmRepositoryImpl
import com.solucionespruna.snoozeloo.alarmtriggered.SnoozelooScheduler
import com.solucionespruna.snoozeloo.database.alarm.AlarmDao
import com.solucionespruna.snoozeloo.database.alarm.AlarmDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AlarmDatabase::class.java,
                "ALARM_DATABASE",
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<AlarmDao> {
        val database = get<AlarmDatabase>()
        database.dao
    }
    single { AlarmRepositoryImpl(get()) } bind AlarmRepository::class
    single { SnoozelooScheduler(get()) }

    viewModelOf(::AlarmDetailViewModel)
    viewModelOf(::AlarmsViewModel)
}
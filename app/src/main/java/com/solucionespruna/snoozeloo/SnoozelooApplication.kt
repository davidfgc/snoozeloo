package com.solucionespruna.snoozeloo

import android.app.Application
import com.solucionespruna.snoozeloo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SnoozelooApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SnoozelooApplication)
            modules(appModule)
        }
    }
}
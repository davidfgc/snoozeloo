package com.solucionespruna.snoozeloo.alarmtriggered

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val name = intent?.let {
            intent.getStringExtra("name")
        } ?: "No Name"
        Log.d("DFG", "onReceive... $name")
    }
}
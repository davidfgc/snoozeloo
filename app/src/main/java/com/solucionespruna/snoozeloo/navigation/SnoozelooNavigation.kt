package com.solucionespruna.snoozeloo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solucionespruna.snoozeloo.alarm.AlarmDetailScreen
import com.solucionespruna.snoozeloo.alarms.AlarmsScreen

@Composable
fun SnoozelooNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "alarms-list") {
        composable("alarms-list") {
            AlarmsScreen {
                navController.navigate("alarm-detail")
            }
        }
        composable("alarm-detail") {
            AlarmDetailScreen()
        }
    }
}
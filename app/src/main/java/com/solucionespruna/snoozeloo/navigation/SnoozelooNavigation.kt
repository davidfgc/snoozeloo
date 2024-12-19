package com.solucionespruna.snoozeloo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.solucionespruna.snoozeloo.alarm.ui.AlarmDetailScreen
import com.solucionespruna.snoozeloo.alarms.AlarmsScreen
import com.solucionespruna.snoozeloo.alarmtriggered.ActiveAlarmScreen
import kotlinx.serialization.Serializable

@Composable
fun SnoozelooNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Alarms) {
        composable<Routes.Alarms> {
            AlarmsScreen {
                navController.navigateTo(Routes.AlarmDetail)
            }
        }
        composable<Routes.AlarmDetail> {
            AlarmDetailScreen {
                navController.navigateTo(Routes.Alarms)
            }
        }
        composable<Routes.ActiveAlarm>(
            deepLinks = listOf(
                navDeepLink<Routes.ActiveAlarm>(
                    basePath = "https://solucionespruna.com/active-alarm"
                )
            )
        ) {
            val id = it.toRoute<Routes.ActiveAlarm>().id
            ActiveAlarmScreen(id) {
                navController.navigateTo(Routes.Alarms)
            }
        }
    }
}

private fun NavController.navigateTo(route: Routes) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(route) {
            inclusive = false
        }
    }
}

sealed interface Routes {
    @Serializable data object Alarms: Routes
    @Serializable data object AlarmDetail: Routes
    @Serializable data class ActiveAlarm(val id: Long): Routes
}

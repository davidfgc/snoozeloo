package com.solucionespruna.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.solucionespruna.snoozeloo.navigation.SnoozelooNavigation
import com.solucionespruna.snoozeloo.ui.theme.SnoozelooTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                KoinAndroidContext {
                    SnoozelooNavigation()
                }
            }
        }
    }

}
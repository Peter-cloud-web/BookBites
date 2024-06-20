package com.example.bookbites

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.sceens.PostBookScreen
import com.example.bookbites.ui.theme.AnimatedSplashScreenTheme
import com.example.navigation.AppNavigation
import com.example.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var sessionManager: SessionManager

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(applicationContext)
        setContent {
            AnimatedSplashScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onTertiary

                ) {

                    AppNavigation(sessionManager = sessionManager)
                }
            }
        }
    }
}

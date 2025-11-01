package com.example.facultyofexactscience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.tv.material3.Surface
import com.example.facultyofexactscience.faculty.presentation.TvAnnouncementScreen
import com.example.facultyofexactscience.ui.theme.FacultyOfExactScienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.facultyofexactscience.di.AppModule.ensureInitialized()

            FacultyOfExactScienceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TvAnnouncementScreen()
                }
            }
        }
    }
}



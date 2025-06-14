package com.example.facultyofexactscience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Surface
import com.example.facultyofexactscience.faculty.presentation.components.Container
import com.example.facultyofexactscience.faculty.presentation.components.Sidebar
import com.example.facultyofexactscience.ui.theme.FacultyOfExactScienceTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            FacultyOfExactScienceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Sidebar()
            Container()
        }
    }
}

@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1080,
    heightDp = 720
)
@Composable
fun GreetingPreviedw() {
    FacultyOfExactScienceTheme {
        Main()
    }
}

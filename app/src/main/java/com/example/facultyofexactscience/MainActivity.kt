package com.example.facultyofexactscience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Devices.TV_1080p
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Surface
import com.example.facultyofexactscience.faculty.presentation.components.Container
import com.example.facultyofexactscience.faculty.presentation.components.Sidebar
import com.example.facultyofexactscience.ui.theme.FacultyOfExactScienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    Box {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Sidebar()
            Container()
        }
    }
}

@Preview(
   device = Devices.TV_720p,
    showBackground = true
)
@Composable
fun GreetingPreview() {
    FacultyOfExactScienceTheme {
        Main()
    }
}
@Preview(device = TV_1080p,
    showBackground = true
)
@Composable
fun GreetingPreviedw() {
    FacultyOfExactScienceTheme {
        Main()
    }
}

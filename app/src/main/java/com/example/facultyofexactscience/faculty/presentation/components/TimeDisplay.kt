package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.ui.theme.textColor
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimeDisplay() {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    var currentDate by remember { mutableStateOf(getCurrentDate()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            currentDate = getCurrentDate()
            delay(1000L)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
         //   .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = currentTime,
           style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = currentDate,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}
fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("EEE,d MMMM",
        Locale.getDefault())
    return dateFormat.format(Date())
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm",Locale.getDefault())
    return sdf.format(Date())
}

@Preview
@Composable
private fun TimedisPrev() {
    TimeDisplay()
    
}
package com.example.facultyofexactscience.presentation.ui.event

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.presentation.ui.getCurrentTime
import kotlinx.coroutines.delay

val events = listOf(
    Event(
        title = "Morning Meeting",
        description = "Discuss team updates and tasks.",
        date = "2024-12-03",
        time = Time("09:00", "10:00"),
        isCurrent = true
    ),
    Event(
        title = "Lunch Break",
        description = "Enjoy a relaxing lunch.",
        date = "2024-12-03",
        time = Time("19:30", "20:15")
    ),
    Event(
        title = "Evening Workout",
        description = "Workout session at the gym.",
        date = "2024-12-03",
        time = Time("18:00", "19:00")
    )
)

@SuppressLint("NewApi")
@Composable
fun EventSlider(
    modifier: Modifier = Modifier
) {

    var updatedEvents by remember { mutableStateOf(events) }

    LaunchedEffect(Unit) {
        val currentTime = getCurrentTime()
        updatedEvents = updatedEvents.map { event ->
            val isCurrent = event.time.startingTime <= currentTime && event.time.endingTime >= currentTime
            event.copy(isCurrent = isCurrent)
        }
        delay(1000)
    }

    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        events.forEachIndexed { _, event ->
            EventItem(event = event)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
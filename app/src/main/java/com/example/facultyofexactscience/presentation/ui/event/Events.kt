package com.example.facultyofexactscience.presentation.ui.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.Event

@Composable
fun EventSlider(
    modifier: Modifier = Modifier
) {
    val events = listOf(
        Event("Event 1", "Lorem ipsum dolor sit amet, conslit.", "Date 1", "Time 1"),
        Event("Event 2", "Lorem ipsum dolor sit amet, conslit.", "Date 2", "Time 2"),
        Event("Event 3", "Lorem ipsum dolor sit amet, conslit.", "Date 3", "Time 3"),
    )

    val eventIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        events.forEachIndexed { index, event ->
            val isCurrent = index == eventIndex
            EventItem(event = event, isCurrent = isCurrent)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
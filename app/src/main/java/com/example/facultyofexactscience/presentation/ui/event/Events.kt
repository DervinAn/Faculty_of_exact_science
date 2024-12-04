package com.example.facultyofexactscience.presentation.ui.event

/**
fun EventSlider(
    modifier: Modifier = Modifier
) {
    val events = listOf(Event("Event 1", "Lorem ipsum dolor sit amet, conslit.", "Date 1", "Time 1"), Event("Event 2", "Lorem ipsum dolor sit amet, conslit.", "Date 2", "Time 2"),
        Event("Event 3", "Lorem ipsum dolor sit amet, conslit.", "Date 3", "Time 3"),)
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
}*/
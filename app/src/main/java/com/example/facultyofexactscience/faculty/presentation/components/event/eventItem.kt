package com.example.facultyofexactscience.faculty.presentation.components.event

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Time
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.containerColor
import com.example.facultyofexactscience.ui.theme.contentColor
import com.example.facultyofexactscience.ui.theme.focusedContainerColor
import com.example.facultyofexactscience.ui.theme.focusedContentColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**fun EventItem(
    event: Event,
    isCurrent: Boolean
) {
    val backgroundColor = if (isCurrent) Color(0xFF34693F) else Color.White
    val textColor = if (isCurrent) Color.White else Color(0xFF34693F)
    val borderColor = if (isCurrent) Color(0xFF757D74) else Color(0xFF34693F)
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(14.dp)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "08",
                    color = textColor,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Feb",
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier,

                ) {
                Text(
                    text = event.title,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.description,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge,

                    )
            }
        }
    }
}
*/

@Composable
fun EventItemNew(
    event: Event,
    modifier: Modifier = Modifier,
    isSelected: Boolean,

    ) {
    Card(
        onClick = {},
        modifier = modifier
            .width(125.dp)
            .aspectRatio(CardDefaults.VerticalImageAspectRatio)
            .focusable() // Make the card focusable
            ,
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge,
            ),
            border = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge
            )
        ), colors = CardDefaults.colors(
            containerColor = if (isSelected) Color(0xFF34693F) else containerColor, // Green if selected
            focusedContainerColor = focusedContainerColor,
            contentColor = if (isSelected) Color.White else contentColor, // White text if selected
            focusedContentColor = focusedContentColor
        ),
        scale = CardDefaults.scale(focusedScale = if (isSelected) 1.02f else 1f),
        shape = CardDefaults.shape(ShapeDefaults.ExtraLarge),) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = event.date,
//                        style = MaterialTheme.typography.headlineSmall,,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "event.time",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


@Composable
fun Events(modifier: Modifier = Modifier) {
    val events = listOf(
        Event("Event Title 1", "Lorem ipsum dolor sit amet.", "Dec 5", Time("12", "PM")),
        Event("Event Title 2", "Another event description.", "Dec 6", Time("3", "PM")),
        Event("Event Title 3", "More details about this event.", "Dec 7", Time("6", "PM")),
        Event("Event Title 4", "Final event for the list.", "Dec 8", Time("9", "AM")),
        Event("Event Title 5", "Description for Event 5.", "Dec 9", Time("11", "AM")),
        Event("Event Title 6", "Description for Event 6.", "Dec 10", Time("2", "PM")),
        Event("Event Title 7", "Description for Event 7.", "Dec 11", Time("5", "PM")),
        Event("Event Title 8", "Description for Event 8.", "Dec 12", Time("8", "AM")),
        Event("Event Title 9", "Description for Event 9.", "Dec 13", Time("10", "AM")),
    )

    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val intervalMillis: Long = 3000  // Auto-scroll every 3 seconds for a smoother effect

    LaunchedEffect(selectedIndex) {
        while (true) {
            delay(intervalMillis)
            val nextIndex = (selectedIndex + 1) % events.size
            selectedIndex = nextIndex

            coroutineScope.launch {
                listState.animateScrollToItem(nextIndex, scrollOffset = 0)
            }
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(events) { index, event ->
            EventItemNew(
                event = event,
                isSelected = selectedIndex == index  // Pass selection state
            )
        }
    }
}



@Preview
@Composable
private fun Hehehehehhe() {
  /**  EventItemNew(
        event = Event("Event 1", "Lorem ipsum dolor sit amet, conslit.", "Feb", "21"),
        modifier = Modifier
    )*/
    Events()
}
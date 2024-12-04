package com.example.facultyofexactscience.presentation.ui.event

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.example.facultyofexactscience.Event
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.containerColor
import com.example.facultyofexactscience.ui.theme.contentColor
import com.example.facultyofexactscience.ui.theme.focusedContainerColor
import com.example.facultyofexactscience.ui.theme.focusedContentColor
import kotlinx.coroutines.delay

@Composable
fun EventItem(
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

@Composable
fun EventItemNew(
    event: Event,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null,
    isFocused: Boolean = false
) {
    Card(onClick = {
            // Handle click events
        }, modifier = modifier
            .width(150.dp)
            .aspectRatio(CardDefaults.VerticalImageAspectRatio)
            .focusable() // Make the card focusable
            .then(
                if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier
            ), border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge,
            ),
            border = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge
            )
        ), colors = CardDefaults.colors(
            containerColor = containerColor,
            focusedContainerColor = focusedContainerColor,
            contentColor = contentColor,
            focusedContentColor = focusedContentColor
        ),
        scale = CardDefaults.scale(focusedScale = if (isFocused) 1.05f else 1f,),
        shape = CardDefaults.shape(ShapeDefaults.ExtraLarge),) {
        Box(
            modifier = Modifier.padding(20.dp)
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
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.time,
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
fun Eventssss(modifier: Modifier = Modifier) {
    val events = listOf(
        Event("Event Title", "Lorem ipsum dolor sit amet.", "Dec 5", "12PM"),
        Event("Event Title", "Lorem ipsum dolor sit amet.", "Dec 5", "12PM"),
        Event("Event Title", "Lorem ipsum dolor sit amet.", "Dec 5", "12PM"),
    )
    val intervalMillis: Long = 5000
    val focusRequesters = remember { events.map { FocusRequester() } }
    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(intervalMillis)
            selectedIndex = (selectedIndex + 1) % events.size
            focusRequesters[selectedIndex].requestFocus()
        }
    }

    LazyRow(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(events) { index, event ->
            EventItemNew(
                event = event,
                focusRequester = focusRequesters[index],
                isFocused = selectedIndex == index
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
    Eventssss()
}
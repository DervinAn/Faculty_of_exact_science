// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/EventCarousel.kt
package com.example.facultyofexactscience.events.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.events.domain.Event
import com.example.facultyofexactscience.events.domain.Time
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil

//@Composable
//fun EventCarousel(
//    events: List<Event>,
//    cardHeightDp: Int,
//    corner: Float,
//    typeScale: Float,
//    cardSpacing: Float,
//    modifier: Modifier = Modifier
//) {
//    val visible = 3
//    val pages = ceil(events.size / visible.toFloat()).toInt().coerceAtLeast(1)
//    val state = rememberLazyListState()
//    val scope = rememberCoroutineScope()
//
//    // Auto-rotate pages every 8 seconds if more than 3 events
//    LaunchedEffect(events.size) {
//        if (events.size > visible) {
//            var page = 0
//            while (true) {
//                delay(8000)
//                page = (page + 1) % pages
//                val targetIndex = page * visible
//                scope.launch { state.animateScrollToItem(targetIndex) }
//            }
//        }
//    }
//
//    LazyRow(
//        state = state,
//        modifier = modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(cardSpacing.dp),
//        userScrollEnabled = false
//    ) {
//        if (events.isEmpty()) {
//            // 3 placeholders to keep the rhythm
//            items(3) { idx ->
//                PlaceholderEventCard(
//                    highlighted = idx == 2,
//                    heightDp = cardHeightDp,
//                    corner = corner,
//                    typeScale = typeScale,
//                    modifier = Modifier
//                        //.weight(1f)
//                )
//            }
//        } else {
//            // We still show 3 per view; if more exist, they rotate page-by-page.
//            val padded = events + List((visible - (events.size % visible)) % visible) {
//                // Pad with last event to keep pages full
//                events.last()
//            }
//            itemsIndexed(padded) { index, ev ->
//                val idxInRow = index % visible
//                EventCard(
//                    event = ev,
//                    highlighted = idxInRow == 2, // right-most highlighted like mock
//                    heightDp = cardHeightDp,
//                    corner = corner,
//                    typeScale = typeScale,
//                    modifier = Modifier
//                        .fillParentMaxWidth(1f / visible)
//                )
//            }
//        }
//    }
//}

@Composable
private fun PlaceholderEventCard(
    highlighted: Boolean,
    heightDp: Int,
    corner: Float,
    typeScale: Float,
    modifier: Modifier = Modifier,
) {
    EventCard(
        event = Event(
            title = "Event Title",
            description = "Lorem ipsum dolor sit amet, conslit.",
            date = "2025-02-01",
            time = Time("00:00", "00:00"),
            isCurrent = false
        ),
        highlighted = highlighted,
        heightDp = heightDp,
        corner = corner,
        typeScale = typeScale,
        modifier = modifier
    )
}

@Composable
fun EventCarousel(
    events: List<Event>,
    cardHeightDp: Int,
    corner: Float,
    typeScale: Float,
    cardSpacing: Float,
    modifier: Modifier = Modifier,
) {
    val visible = 3
    val pages = ceil(events.size / visible.toFloat()).toInt().coerceAtLeast(1)
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(events.size) {
        if (events.size > visible) {
            var page = 0
            while (true) {
                delay(8_000L)
                page = (page + 1) % pages
                scope.launch { state.animateScrollToItem(page * visible) }
            }
        }
    }

    LazyRow(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(cardSpacing.dp),
        userScrollEnabled = false
    ) {
        if (events.isEmpty()) {
            // show 3 placeholder cards instead of exploding
            items(3) { idx ->
                PlaceholderEventCard(
                    highlighted = idx == 2,
                    heightDp = cardHeightDp,
                    corner = corner,
                    typeScale = typeScale
                )
            }
        } else {
            val padded = events + List((visible - (events.size % visible)) % visible) {
                events.last()
            }
            itemsIndexed(padded) { index, ev ->
                val idxInRow = index % visible
                EventCard(
                    event = ev,
                    highlighted = idxInRow == 2,
                    heightDp = cardHeightDp,
                    corner = corner,
                    typeScale = typeScale,
                    modifier = Modifier.fillParentMaxWidth(1f / visible)
                )
            }
        }
    }
}


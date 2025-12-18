package com.example.facultyofexactscience.events.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.events.domain.Event
import com.example.facultyofexactscience.events.domain.Time
import kotlin.math.max

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
        onFocused = {},
        modifier = modifier
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun EventCarousel(
    events: List<Event>,
    selectedIndex: Int,
    onEventFocused: (Int) -> Unit,
    cardHeightDp: Int,
    corner: Float,
    typeScale: Float,
    cardSpacing: Float,
    modifier: Modifier = Modifier,
) {
    val visible = 3
    val state = rememberLazyListState()

    // ✅ when selected changes (because hero slide changed), scroll to it
    LaunchedEffect(selectedIndex, events.size) {
        if (events.isNotEmpty()) {
            val safe = selectedIndex.coerceIn(0, events.lastIndex)
            state.animateScrollToItem(safe)
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val sidePadding = 6.dp
        val spacing = cardSpacing.dp
        val contentWidth = maxWidth - sidePadding * 2
        val cardWidth = (contentWidth - spacing * (visible - 1)) / visible

        LazyRow(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(sidePadding),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            userScrollEnabled = false
        ) {
            if (events.isEmpty()) {
                items(visible) { idx ->
                    PlaceholderEventCard(
                        highlighted = idx == 0,
                        heightDp = cardHeightDp,
                        corner = corner,
                        typeScale = typeScale,
                        modifier = Modifier
                    )
                }
            } else {
                itemsIndexed(events) { index, ev ->
                    EventCard(
                        event = ev,
                        highlighted = index == selectedIndex,
                        heightDp = cardHeightDp,
                        corner = corner,
                        typeScale = typeScale,
                        onFocused = { onEventFocused(index) },
                        modifier = Modifier
                            .width(cardWidth)
                    )
                }

                // ✅ only placeholders if fewer than visible (no duplicating last event)
                val missing = max(0, visible - events.size)
                if (missing > 0) {
                    items(missing) { _ ->
                        PlaceholderEventCard(
                            highlighted = false,
                            heightDp = cardHeightDp,
                            corner = corner,
                            typeScale = typeScale,
                            modifier = Modifier.width(cardWidth)
                        )
                    }
                }
            }
        }
    }
}

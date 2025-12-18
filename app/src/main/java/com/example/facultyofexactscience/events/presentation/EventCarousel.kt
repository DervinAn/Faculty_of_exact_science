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
import kotlin.math.max
import kotlin.math.min

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun EventCarousel(
    cardHeightDp: Int, // kept for compatibility (not used heavily now)
    corner: Float,
    typeScale: Float,
    cardSpacing: Float,
    modifier: Modifier = Modifier,
    events: List<Event>,
    isLoading: Boolean,
    selectedIndex: Int,
    onEventFocused: (Int) -> Unit,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(selectedIndex, events.size) {
        if (events.isNotEmpty()) {
            val safe = selectedIndex.coerceIn(0, events.lastIndex)
            listState.animateScrollToItem(safe)
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val sidePadding = 10.dp
        val spacing = cardSpacing.dp
        val contentWidth = maxWidth - sidePadding * 2

        // adaptive visible count based on width
        val desiredItemWidthDp = 240f
        val visible = min(
            8,
            max(4, (contentWidth.value / desiredItemWidthDp).toInt())
        )

        val itemWidth = (contentWidth - spacing * (visible - 1)) / visible

        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = sidePadding, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            userScrollEnabled = true
        ) {
            if (isLoading) {
                items(visible) { _ ->
                    // simple skeleton-like placeholder
                    EventRailItem(
                        event = Event(title = "Loading…", description = "", date = null, images = emptyList()),
                        selected = false,
                        corner = corner,
                        typeScale = typeScale,
                        onFocused = {},
                        modifier = Modifier.width(itemWidth)
                    )
                }
            } else {
                itemsIndexed(events) { index, ev ->
                    EventRailItem(
                        event = ev,
                        selected = index == selectedIndex,
                        corner = corner,
                        typeScale = typeScale,
                        onFocused = { onEventFocused(index) },
                        modifier = Modifier.width(itemWidth)
                    )
                }
            }
        }
    }
}

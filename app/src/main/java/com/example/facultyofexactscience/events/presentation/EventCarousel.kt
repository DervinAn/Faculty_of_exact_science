package com.example.facultyofexactscience.events.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.events.domain.Event

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun EventCarousel(
    cardHeightDp: Int,
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
        val sidePadding = 16.dp
        val contentWidth = maxWidth - sidePadding * 2

        // ✅ FULL WIDTH card (one item per "page")
        val itemWidth = contentWidth

        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = sidePadding, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            userScrollEnabled = true
        ) {
            if (isLoading) {
                items(1) {
                    EventRailItem(
                        event = Event(
                            title = "Loading…", description = "", date = null, images = emptyList()
                        ),
                        selected = false,
                        corner = corner,
                        typeScale = typeScale,
                        onFocused = {},
                        modifier = Modifier.width(itemWidth)
                    )
                }
            } else {
                itemsIndexed(events) { index, ev ->
                    val selected = index == selectedIndex

                    val alpha by animateFloatAsState(
                        targetValue = if (selected) 1f else 0.75f,
                        animationSpec = tween(220, easing = FastOutSlowInEasing),
                        label = "cardAlpha"
                    )
                    val scale by animateFloatAsState(
                        targetValue = if (selected) 1.0f else 0.98f,
                        animationSpec = tween(220, easing = FastOutSlowInEasing),
                        label = "cardScale"
                    )

                    EventRailItem(
                        event = ev,
                        selected = selected,
                        corner = corner,
                        typeScale = typeScale,
                        onFocused = { onEventFocused(index) },
                        modifier = Modifier
                            .width(itemWidth)
                            .animateItem(tween(260, easing = FastOutSlowInEasing))
                            .graphicsLayer {
                                this.alpha = alpha
                                scaleX = scale
                                scaleY = scale
                            })
                }
            }
        }
    }
}

package com.example.facultyofexactscience.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.MaterialTheme
import com.example.facultyofexactscience.core.presentation.components.LeftSidebar
import com.example.facultyofexactscience.core.presentation.components.dashboard.BackgroundWire
import com.example.facultyofexactscience.core.presentation.components.dashboard.PartnerRow
import com.example.facultyofexactscience.core.presentation.components.dashboard.rememberTvDims
import com.example.facultyofexactscience.core.presentation.ui.theme.border
import com.example.facultyofexactscience.core.presentation.ui.theme.sideBarBackground
import com.example.facultyofexactscience.events.presentation.EventCarousel
import com.example.facultyofexactscience.events.presentation.HeroBanner

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TvAnnouncementScreen(viewModel: FacultyViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.98f))
    ) {
        val dims = rememberTvDims(maxWidth.value.toInt(), maxHeight.value.toInt())
        val leftW = minOf(maxWidth * 0.31f, dims.leftMaxWidthDp.dp)
        val corner = dims.corner

        val currentQuote = state.quotes.getOrNull(state.quoteIndex)?.text
            ?: "Events are not just gatherings; they spark inspiration and create lasting impressions."

        val heroUrl = state.heroSlides.getOrNull(state.heroSlideIndex)?.imageUrl

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            LeftSidebar(
                quote = currentQuote,
                modifier = Modifier
                    .width(leftW)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = corner.dp, bottomEnd = corner.dp))
                    .background(sideBarBackground)
                    .border(
                        3.dp,
                        border,
                        RoundedCornerShape(topEnd = corner.dp, bottomEnd = corner.dp)
                    ),
                cornerDp = corner,
                gutterDp = dims.gutter
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                BackgroundWire(modifier = Modifier.matchParentSize())

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {

                    HeroBanner(
                        imageUrl = heroUrl,
                        corner = corner,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.60f)
                    )

                    EventCarousel(
                        events = state.events,
                        selectedIndex = state.selectedEventIndex,
                        onEventFocused = { idx -> viewModel.onEventFocused(idx) },
                        cardHeightDp = dims.cardHeightDp,
                        corner = corner,
                        typeScale = dims.typeScale,
                        cardSpacing = dims.cardSpacing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.30f)
                    )

                    PartnerRow(
                        modifier = Modifier.fillMaxWidth(),
                        chipSize = 50.dp
                    )
                }
            }
        }
    }
}

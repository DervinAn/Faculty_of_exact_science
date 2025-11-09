// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/TvAnnouncementScreen.kt
package com.example.facultyofexactscience.faculty.presentation

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.faculty.presentation.components.LeftSidebar
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.BackgroundWire
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.EventCarousel
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.HeroBanner
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.PartnerChip
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.PartnerRow
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.rememberTvDims
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.sideBarBackground

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TvAnnouncementScreen(viewModel: FacultyViewModel = viewModel()) {
    val heroImages by viewModel.heroImages.collectAsState()
    val heroIdx by viewModel.heroIndex.collectAsState()
    val events by viewModel.events.collectAsState()
    val quotes by viewModel.quotes.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.98f))
    ) {
        val dims = rememberTvDims(maxWidth.value.toInt(), maxHeight.value.toInt())
        val leftW = minOf(maxWidth * 0.31f, dims.leftMaxWidthDp.dp)
        val gutter = dims.gutter.dp
        val corner = dims.corner

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement
                .SpaceBetween,
            verticalAlignment = Alignment
                .Top,

        ) {
            val quoteIndex = remember { mutableIntStateOf(0) }.intValue
            val currentQuote = quotes.getOrNull(quoteIndex)?.text
                ?: "Events are not just gatherings; they spark inspiration and create lasting impressions."

            LeftSidebar(
                quote = currentQuote,
                modifier = Modifier
                    .width(leftW)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = corner.dp,
                        bottomEnd = corner.dp))
                    .background(sideBarBackground)
                    .border( 3.dp, border, RoundedCornerShape(topEnd = corner.dp, bottomEnd = corner.dp)),
                cornerDp = corner,
                gutterDp = dims.gutter
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                BackgroundWire(
                    modifier = Modifier
                        .matchParentSize()      // cover the whole right panel
                )

                // ----- Foreground content: hero, events, partners -----
                Column(
                    modifier = Modifier
                        .fillMaxSize(),        // keep your existing spacing outside this Box
                    verticalArrangement = Arrangement.spacedBy(gutter)
                ) {
                    HeroBanner(
                        imageUrl = heroImages.getOrNull(heroIdx)
                            ?: "http://192.168.1.13:8000/events_images/1",
                        corner = corner,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.60f)
                    )

                    EventCarousel(
                        events = events,
                        cardHeightDp = dims.cardHeightDp,
                        corner = corner,
                        typeScale = dims.typeScale,
                        cardSpacing = dims.cardSpacing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.28f)
                    )

                    PartnerRow(
                        modifier = Modifier.fillMaxWidth()

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:tv_4k",
  //  widthDp = 1280,
    //heightDp = 720,
    )
@Composable
private fun PreviewTvAnnouncementScreen() {
    Surface { TvAnnouncementScreen() }
}

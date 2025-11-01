// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/TvAnnouncementScreen.kt
package com.example.facultyofexactscience.faculty.presentation

import android.R.attr.delay
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.tv.material3.Text
import com.example.facultyofexactscience.faculty.presentation.components.QuoteCard
import com.example.facultyofexactscience.faculty.presentation.components.Quotes
import com.example.facultyofexactscience.faculty.presentation.components.SocialQrPanel
import com.example.facultyofexactscience.faculty.presentation.components.TimeDisplay
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.EventCarousel
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.HeroBanner
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.PartnersStrip
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.SocialPanel
import com.example.facultyofexactscience.faculty.presentation.components.dashboard.rememberTvDims
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.sideBarBackground
import kotlinx.coroutines.delay

/**
 * Redesigned to visually match the poster:
 * - Left: centered header/time/date, illustration card, quote, social+QR.
 * - Right: large rounded hero, compact events row, logos strip bottom.
 * Names/args preserved to match existing logic and repositories.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TvAnnouncementScreen(viewModel: FacultyViewModel = viewModel()) {
    // ── Data from VM ──
    val heroImages by viewModel.heroImages.collectAsState()
    val heroIdx by viewModel.heroIndex.collectAsState()
    val heroUrl = heroImages.getOrNull(heroIdx)
    val events by viewModel.events.collectAsState()
    val quotes by viewModel.quotes.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.98f))
            .padding(16.dp)
    ) {
        val dims = rememberTvDims(maxWidth.value.toInt(), maxHeight.value.toInt())
        val leftW = minOf(maxWidth * 0.28f, dims.leftMaxWidthDp.dp)
        val gutter = dims.gutter.dp
        val corner = dims.corner

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(gutter)
        ) {
            // ────────────────────── LEFT PANEL ──────────────────────
            Column(
                modifier = Modifier
                    .width(leftW)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(corner.dp))
                    .background(sideBarBackground)
                    .border(3.dp, border, RoundedCornerShape(corner.dp))
                    .padding(gutter * 1.25f),
                verticalArrangement = Arrangement.spacedBy(gutter * 1.1f)
            ) {
                // Header + live clock
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("UTMB", style = MaterialTheme.typography.displaySmall)
                    Text("Faculty of Science Exact", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(8.dp))
                    TimeDisplay()
                }

                // Small illustration (blue pin) – placeholder for a real image if you have one
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 140.dp, max = 180.dp)
                        .clip(RoundedCornerShape((corner - 6).dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.90f))
                )

                // Rotating quote
                val currentQuote = quotes.getOrNull(remember { mutableIntStateOf(0).also { idx ->
//                    LaunchedEffect(quotes) {
//                        while (true) {
//                            delay(10_000L)
//                            idx.intValue = (idx.intValue + 1) % quotes.size.coerceAtLeast(1)
//                        }
//                    }
                }.intValue })?.text ?: "Loading quote..."
                QuoteCard(currentQuote)

                // Social + QR
                SocialQrPanel(Modifier.fillMaxWidth())
            }

            // ────────────────────── RIGHT PANEL ──────────────────────
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(gutter)
            ) {
                // Hero (60 % of height)
                HeroBanner(
                    imageUrl = heroUrl,
                    corner = corner,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.60f)
                )

                // Event carousel (compact row)
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

                // Partners strip (thin fixed height)
                PartnersStrip(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
            }
        }
    }
}


/* ---------- Preview ---------- */

@Preview(showBackground = true, widthDp = 1280, heightDp = 720)
@Composable
private fun PreviewTvAnnouncementScreen() {
    Surface { TvAnnouncementScreen() }
}

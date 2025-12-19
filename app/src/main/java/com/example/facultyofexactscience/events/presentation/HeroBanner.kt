package com.example.facultyofexactscience.events.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.facultyofexactscience.R

/**
 * Keep this if other screens already call HeroBanner(imageUrl,...)
 */
/**
 * New TV-friendly hero announcement (image + readable text overlay + dots + autoplay progress).
 */
@Composable
fun HeroAnnouncementCard(
    imageUrl: String?,
    title: String,
    dateLine: String,
    description: String,
    corner: Float,
    modifier: Modifier = Modifier,
    slideIndex: Int,
    slideCount: Int,
    autoplayMs: Long,
    showProgress: Boolean = true,
) {
    val shape = RoundedCornerShape(corner.dp)

    val progress = remember { Animatable(0f) }
    LaunchedEffect(slideIndex, slideCount, autoplayMs, showProgress) {
        progress.snapTo(0f)
        if (showProgress && slideCount > 1) {
            progress.animateTo(
                1f, animationSpec = tween(
                    durationMillis = autoplayMs.coerceAtMost(Int.MAX_VALUE.toLong()).toInt(),
                    easing = LinearEasing
                )
            )
        }
    }

    Box(
        modifier = modifier
            .padding(16.dp)
            .clip(shape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                .data(imageUrl).crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.four),
            error = painterResource(R.drawable.faculte_map),
            modifier = Modifier.fillMaxSize()
        )

        // Contrast overlay (top transparent -> bottom dark)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.11f to Color(0x00000000),
                        1f to Color(0xB3000000),
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
           // Spacer(Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DotsIndicator(
                    count = slideCount, index = slideIndex
                )

                if (showProgress && slideCount > 1) {
                    LinearProgressIndicator(
                        progress = progress.value,
                        modifier = Modifier
                            .width(180.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(99.dp)),
                    )
                }
            }
        }
    }
}

@Composable
private fun DotsIndicator(
    count: Int,
    index: Int,
    modifier: Modifier = Modifier,
) {
    if (count <= 1) return

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count.coerceAtMost(12)) { i ->
            val active = i == index.coerceIn(0, count - 1)
            Box(
                modifier = Modifier
                    .width(if (active) 18.dp else 8.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(if (active) Color.White else Color.White.copy(alpha = 0.45f))
            )
        }
        if (count > 12) {
            Text(
                text = "${index + 1}/$count",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}


package com.example.facultyofexactscience.presentation.ui.caroussel

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.rememberCarouselState
import com.example.facultyofexactscience.R


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NewCaroussel(modifier: Modifier = Modifier) {
    val items = listOf(
        R.drawable.faculte_map,
        R.drawable.one,
        R.drawable.three,
        R.drawable.four
    )
    val carouselState = rememberCarouselState()
    Carousel(
        modifier = modifier
            .clip(ShapeDefaults.ExtraLarge),
        itemCount = items.size,
        carouselState = carouselState,
        carouselIndicator = {
            CarouselIndicator(
                itemCount = items.size,
                activeItemIndex = carouselState.activeItemIndex
            )
        },
        contentTransformStartToEnd = fadeIn(tween(durationMillis = 1000))
            .togetherWith(fadeOut(tween(durationMillis = 1000))),
        contentTransformEndToStart = fadeIn(tween(durationMillis = 1000))
            .togetherWith(fadeOut(tween(durationMillis = 1000))),
        autoScrollDurationMillis = 5000,
        content = {
            Image(
                painter = painterResource(id = items[it]),
                contentDescription = "Carousel Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

/**
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BoxScope.CarouselIndicator(
    itemCount: Int,
    activeItemIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .graphicsLayer {
                clip = true
                shape = ShapeDefaults.ExtraSmall
            }
            .align(Alignment.BottomEnd)
    ) {
        CarouselDefaults.IndicatorRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            itemCount = itemCount,
            indicator = { isActive ->
                val activeColor = Color(0xff34693F)
                val inactiveColor = activeColor.copy(alpha = 0.45f)
                Box(
                    modifier =
                    Modifier
                        .size(
                            20.dp
                        )
                        .background(
                            color = if (isActive) activeColor else inactiveColor,
                            shape = CircleShape,
                        ),
                )
            },
            activeItemIndex = activeItemIndex,
        )
    }
}*/

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BoxScope.CarouselIndicator(
    itemCount: Int,
    activeItemIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(28.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .graphicsLayer {
                clip = true
                shape = ShapeDefaults.ExtraSmall
            }
            .align(Alignment.BottomEnd)
    ) {
        CarouselDefaults.IndicatorRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            itemCount = itemCount,
            activeItemIndex = activeItemIndex,
        )
    }
}


@Preview(
    name = "TV Preview", showBackground = true, widthDp = 1280, heightDp = 720
)
@Composable
private fun NewCarousselPrev() {

    Box(modifier = Modifier.padding(16.dp)) {
        NewCaroussel()
    }
}
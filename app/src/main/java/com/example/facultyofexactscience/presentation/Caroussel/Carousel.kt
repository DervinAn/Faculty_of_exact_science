package com.example.facultyofexactscience.presentation.Caroussel

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.delay


@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewCaroussel(modifier: Modifier = Modifier) {
    val items = listOf(
        R.drawable.faculte_map,
        R.drawable.one,
        R.drawable.three,
        R.drawable.four
    )
    val carouselState = rememberCarouselState()
    var isCarouselFocused by remember { mutableStateOf(true) }
    val alpha = if (isCarouselFocused) {
        1f
    } else {
        0f
    }

    Carousel(
        modifier = modifier

            //.padding(start = padding.start, end = padding.start, top = padding.top)
            //   .border(width = widht, color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha), shape = ShapeDefaults.Medium,)
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
            Box() {
                Image(
                    painter = painterResource(id = items[it]),
                    contentDescription = "Carousel Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    )
}


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
                            width = 8.dp,
                            height = 8.dp
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
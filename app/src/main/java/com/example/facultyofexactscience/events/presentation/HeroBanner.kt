package com.example.facultyofexactscience.events.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.facultyofexactscience.R

@Composable
fun HeroBanner(
    imageUrl: String?,
    corner: Float,
    modifier: Modifier = Modifier,
    blurRadius: Dp = 0.dp,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.four),
        error = painterResource(R.drawable.faculte_map),
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(corner.dp))
            .then(if (blurRadius > 0.dp) Modifier.blur(blurRadius) else Modifier)
    )
}

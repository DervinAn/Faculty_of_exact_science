package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
 fun PartnerChip(
    modifier: Modifier = Modifier,
    chipSize: Dp = 72.dp,
    chipPadding: Dp = 10.dp,
    blurColor: Color = Color(0x69FFFCFC),     // same tone you used
    logoRes: Int,
    logoUrl: String? = null
) {

    Box(modifier = modifier.size(chipSize), contentAlignment = Alignment.Center) {

        // 1) BACK LAYER: pure color circle with blur
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .background(blurColor)
                .blur(20.dp) // adjust blur radius to taste
        )

        // 2) FRONT LAYER: photo circle
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .background(Color.Transparent)
                .padding(chipPadding)
        ) {
            if (!logoUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = "Partner logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            } else if (logoRes != null) {
                Image(
                    painter = painterResource(logoRes),
                    contentDescription = "Partner logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
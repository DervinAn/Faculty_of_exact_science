package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Contribution(contributorsImage: List<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((10).dp) // Adjust overlap effect
    ) {
        contributorsImage.forEach { id ->
            Box(
                modifier = Modifier
                    .size(60.dp) // Adjust size
                    .clip(CircleShape) // Ensure circular shape
                    .background(Color.White) // Optional background color
                    .border(2.dp, Color.White, CircleShape) // Add border
            ) {
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "Contributor",
                    contentScale = ContentScale.Crop, // Crop to fit circle
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape) // Clip to circle again (double-check)
                )
            }
        }
    }
}

package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Contribution(contributorsImage: List<Int>) {
    contributorsImage.forEach { id ->
        Image(
            painter = painterResource(id = id),
            contentDescription = "Contributors",
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape)
        )
    }
}
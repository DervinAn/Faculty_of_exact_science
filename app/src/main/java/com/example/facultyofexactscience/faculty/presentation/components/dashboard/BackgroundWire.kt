// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/components/dashboard/BackgroundWire.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.facultyofexactscience.R


@Composable
fun BackgroundWire(
    modifier: Modifier = Modifier,
    opacity: Float = 1f
) {
    Image(
        painter = painterResource(R.drawable.background_vectour_wire),
        contentDescription = null,
        contentScale = ContentScale.Crop,   // fill the available bounds
        modifier = modifier.alpha(opacity)
    )
}

package com.example.facultyofexactscience.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun Canva(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val wavePath = Path().apply {
            moveTo(0f, size.height * 0.87f)
            quadraticBezierTo(
                size.width * 0.25f, size.height * 0.9f,
                size.width * 0.4f, size.height * 0.95f
            )
            quadraticBezierTo(
                size.width * 0.8f, size.height * 0.75f,
                size.width, size.height * 0.8f
            )
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawPath(
            path = wavePath,
            color = Color(0xFF0B6623),
            style = Fill
        )
    }

}

package com.example.facultyofexactscience.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import com.example.facultyofexactscience.ui.theme.canvaColor

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
                size.width * 0.8f, size.height * 0.7f,
                size.width, size.height * 0.8f
            )
            quadraticBezierTo(
                size.width * 0.8f, size.height * 0.8f,
                size.width, size.height * 0.8f
            )
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawPath(
            path = wavePath,
            color = canvaColor,
            style = Fill
        )
    }

}


@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1080,
    heightDp = 720
)@Composable
private fun CanvaPrev() {
    Canva()


}


@Composable
fun Canva1(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val wavePath = Path().apply {
            moveTo(0f, 93.282f)
            lineTo(29.3867f, 87.5914f)
            cubicTo(57.76f, 81.9008f, 115.52f, 70.5196f, 173.28f, 87.5914f)
            cubicTo(232.053f, 104.663f, 289.813f, 150.188f, 347.573f, 167.26f)
            cubicTo(405.333f, 184.332f, 463.093f, 172.95f, 520.853f, 150.188f)
            cubicTo(578.613f, 127.426f, 637.387f, 93.282f, 695.147f, 59.1384f)
            cubicTo(752.907f, 24.9948f, 810.667f, -9.14875f, 868.427f, 2.23245f)
            cubicTo(926.187f, 13.6136f, 983.947f, 70.5196f, 1042.72f, 98.9726f)
            cubicTo(1100.48f, 127.426f, 1158.24f, 127.426f, 1186.61f, 127.426f)
            lineTo(1216f, 127.426f)
            lineTo(1216f, 264f)
            lineTo(1186.61f, 264f)
            cubicTo(1158.24f, 264f, 1100.48f, 264f, 1042.72f, 264f)
            cubicTo(983.947f, 264f, 926.187f, 264f, 868.427f, 264f)
            cubicTo(810.667f, 264f, 752.907f, 264f, 695.147f, 264f)
            cubicTo(637.387f, 264f, 578.613f, 264f, 520.853f, 264f)
            cubicTo(463.093f, 264f, 405.333f, 264f, 347.573f, 264f)
            cubicTo(289.813f, 264f, 232.053f, 264f, 173.28f, 264f)
            cubicTo(115.52f, 264f, 57.76f, 264f, 29.3867f, 264f)
            lineTo(0f, 264f)
            lineTo(0f, 93.282f)
            close()
        }

        drawPath(
            path = wavePath,
            color = canvaColor,
            style = Fill
        )
    }
}
/**
@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1080,
    heightDp = 720
)@Composable
private fun hhhhhhhh() {
   // Canva1()
}
*/
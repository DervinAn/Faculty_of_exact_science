// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/Dimensions.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

enum class TvSizeClass { Small, Medium, Large, XLarge }

data class TvDims(
    val gutter: Float,
    val corner: Float,
    val leftMaxWidthDp: Int,
    val heroHeightPct: Float,
    val cardHeightDp: Int,
    val cardSpacing: Float,
    val typeScale: Float,          // new: typography scale factor
)

@Composable
fun rememberTvDims(maxWidthDp: Int, maxHeightDp: Int): TvDims {
    val cls = when {
        maxWidthDp >= 2000 -> TvSizeClass.XLarge
        maxWidthDp >= 1600 -> TvSizeClass.Large
        maxWidthDp >= 1280 -> TvSizeClass.Medium
        else               -> TvSizeClass.Small
    }
    return remember(cls) {
        when (cls) {
            TvSizeClass.Small -> TvDims(8f,16f,360,0.52f,160,8f, 0.92f)
            TvSizeClass.Medium-> TvDims(12f,20f,420,0.55f,180,12f, 1.00f)
            TvSizeClass.Large -> TvDims(16f,24f,480,0.58f,200,16f, 1.08f)
            TvSizeClass.XLarge-> TvDims(20f,28f,560,0.60f,220,18f, 1.18f)
        }
    }
}

// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/HeroBanner.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import coil.size.Scale
import coil.size.Size
import com.example.facultyofexactscience.R

//@Composable
//fun HeroBanner(
//    corner: Float,
//    modifier: Modifier = Modifier,
//    imageUrl: String? = null
//) {
//    val ctx = LocalContext.current
//    AsyncImage(
//        model = ImageRequest.Builder(ctx)
//            .data(imageUrl ?: R.drawable.background_vectour_wire)
//            .crossfade(true)
//            .scale(Scale.FILL)
//            .size(Size.ORIGINAL)
//            .build(),
//        contentDescription = null,
//        modifier = modifier.clip(RoundedCornerShape(corner.dp))
//    )
//}
@Composable
fun HeroBanner(
    imageUrl: String?,
    corner: Float,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl ?: R.drawable.background_vectour_wire)
            .crossfade(true)
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(corner.dp))
    )
}


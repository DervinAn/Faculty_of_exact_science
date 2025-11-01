// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/EventCard.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.faculty.domain.Event

//@Composable
//fun EventCard(
//    event: Event,
//    highlighted: Boolean,
//    heightDp: Int,
//    corner: Float,
//    typeScale: Float,
//    modifier: Modifier = Modifier
//) {
//    val shape = RoundedCornerShape(corner.dp)
//    val outlineColor = MaterialTheme.colorScheme.border.copy(alpha = 0.5f)
//    val fillColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
//
//    Column(
//        modifier = modifier
//            .height(heightDp.dp)
//            .clip(shape)
//            .then(
//                if (highlighted)
//                    Modifier.background(fillColor)
//                else
//                    Modifier.border(2.dp, outlineColor, shape)
//            )
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        // Date badge
//        val (day, mon) = toDayMonth(event.date)
//        Column(
//            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.spacedBy(2.dp)
//        ) {
//            Text(
//                day,
//                style = MaterialTheme.typography.headlineLarge,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                mon,
//                style = MaterialTheme.typography.labelLarge
//            )
//        }
//
//        // Title + snippet
//        Column {
//            Text(
//                event.title,
//                style = MaterialTheme.typography.titleMedium,
//                fontWeight = FontWeight.SemiBold,
//                maxLines = 1
//            )
//            Spacer(Modifier.height(4.dp))
//            Text(
//                event.description,
//                style = MaterialTheme.typography.bodyMedium,
//                maxLines = 2
//            )
//        }
//    }
//}

@Composable
fun EventCard(
    event: Event,
    highlighted: Boolean,
    heightDp: Int,
    corner: Float,
    typeScale: Float,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(corner.dp)
    val outline = MaterialTheme.colorScheme.border.copy(alpha = 0.5f)
    val fill = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)

    Column(
        modifier = modifier
            .height(heightDp.dp)
            .clip(shape)
            .then(if (highlighted) Modifier.background(fill) else Modifier.border(2.dp, outline, shape))
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val (day, mon) = toDayMonth(event.date)
        Column(horizontalAlignment = Alignment.Start) {
            Text(day, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text(mon, style = MaterialTheme.typography.labelLarge)
        }
        Column {
            Text(event.title, style = MaterialTheme.typography.titleMedium, maxLines = 1)
            Spacer(Modifier.height(4.dp))
            Text(event.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
        }
    }
}
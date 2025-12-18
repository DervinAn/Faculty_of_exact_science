// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/EventCard.kt
package com.example.facultyofexactscience.events.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.core.presentation.components.dashboard.toDayMonth
import com.example.facultyofexactscience.events.domain.Event

@Composable
fun EventCard(
    event: Event,
    highlighted: Boolean,
    heightDp: Int,
    corner: Float,
    typeScale: Float,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(corner.dp)

    // Colors to mimic the screenshot
    val outline = MaterialTheme.colorScheme.border.copy(alpha = 0.5f)
    val bg =
        if (highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val titleColor =
        if (highlighted) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    val bodyColor = if (highlighted) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
    val dateColor = titleColor

    Column(
        modifier = modifier
            .height(heightDp.dp)
            .clip(shape)
            .then(
                if (highlighted) Modifier.background(bg)
                else Modifier
                    .background(bg)
                    .border(2.dp, outline, shape)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // DATE (big day + small month, stacked)
        val (day, mon) = toDayMonth(event.date)
        Column(
            horizontalAlignment = Alignment
                .Start,
            verticalArrangement = Arrangement
                .spacedBy(2.dp)
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize * (if (highlighted) 1.05f else 1f) * typeScale
                ),
                fontWeight = FontWeight.ExtraBold,
                color = dateColor
            )
            Text(
                text = mon,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = MaterialTheme.typography.labelLarge.fontSize * 0.95f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                color = dateColor.copy(alpha = if (highlighted) 0.9f else 0.8f),
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.0f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
                color = titleColor
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize * 0.95f * typeScale,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 0.95f
                ),
                maxLines = 2,
                color = bodyColor
            )
        }
    }
}

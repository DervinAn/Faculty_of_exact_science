package com.example.facultyofexactscience.events.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    onFocused: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(corner.dp)

    var isFocused by remember { mutableStateOf(false) }

    val outline = MaterialTheme.colorScheme.border.copy(alpha = 0.55f)
    val focusedOutline = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)

    val bgTarget =
        if (highlighted) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface

    val bg by animateColorAsState(bgTarget, label = "eventCardBg")

    val titleColor =
        if (highlighted) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurface

    val bodyColor =
        if (highlighted) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)

    Column(
        modifier = modifier
            .height(heightDp.dp)
            .clip(shape)
            .background(bg)
            .border(
                width = if (isFocused) 3.dp else 2.dp,
                color = if (isFocused) focusedOutline else outline,
                shape = shape
            )
            .onFocusChanged {
                val nowFocused = it.isFocused
                if (nowFocused && !isFocused) onFocused()
                isFocused = nowFocused
            }
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val (day, mon) = toDayMonth(event.date)

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize * (if (highlighted) 1.08f else 1.02f) * typeScale
                ),
                fontWeight = FontWeight.ExtraBold,
                color = titleColor
            )
            Text(
                text = mon,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = MaterialTheme.typography.labelLarge.fontSize * 1.05f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                color = titleColor.copy(alpha = 0.85f),
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.10f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = titleColor
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize * 1.02f * typeScale,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.05f
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = bodyColor
            )
        }
    }
}

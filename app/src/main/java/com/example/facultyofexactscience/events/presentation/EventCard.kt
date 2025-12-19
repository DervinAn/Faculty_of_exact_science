package com.example.facultyofexactscience.events.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.core.presentation.components.dashboard.toDayMonth
import com.example.facultyofexactscience.events.domain.Event

/**
 * Bottom rail item (TV-friendly).
 */
@Composable
fun EventRailItem(
    event: Event,
    selected: Boolean,
    corner: Float,
    typeScale: Float,
    onFocused: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape((corner * 0.75f).dp)

    var isFocused by remember { mutableStateOf(false) }
    val focused = selected || isFocused

    val bg by animateColorAsState(
        targetValue = if (focused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        label = "railBg"
    )
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.05f else 1f,
        label = "railScale"
    )

    val outline = MaterialTheme.colorScheme.border.copy(alpha = 0.55f)
    val focusedOutline = MaterialTheme.colorScheme.primary.copy(alpha = 0.95f)

    val titleColor =
        if (focused) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    val (day, mon) = toDayMonth(event.date)

    Row(
        modifier = modifier
            .height(118.dp)
            .scale(scale)
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
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Date pill
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (focused) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.14f)
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
                )
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.10f * typeScale,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = titleColor
            )
            Text(
                text = mon,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = MaterialTheme.typography.labelMedium.fontSize * 1.05f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                color = titleColor.copy(alpha = 0.9f)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.10f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = titleColor
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize * 1.05f * typeScale
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = titleColor.copy(alpha = 0.85f)
            )
        }

        Spacer(Modifier.width(2.dp))
    }
}

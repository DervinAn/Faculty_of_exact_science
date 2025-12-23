package com.example.facultyofexactscience.events.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.core.presentation.components.dashboard.toDayMonth
import com.example.facultyofexactscience.core.presentation.components.ui.theme.goldenYellow
import com.example.facultyofexactscience.core.presentation.components.ui.theme.sideBarBackground
import com.example.facultyofexactscience.events.domain.Event

@Composable
fun EventRailItem(
    event: Event,
    selected: Boolean,
    corner: Float,
    typeScale: Float,
    onFocused: () -> Unit,
    modifier: Modifier = Modifier,
    heightDp: Int = 118,
) {
    val shapeC = RoundedCornerShape((corner * 0.75f).dp)

    var isFocused by remember { mutableStateOf(false) }
    val focused = selected || isFocused

    // ✅ Force fully opaque background (prevents any bleed-through)
    val baseBg = sideBarBackground.copy(alpha = 1f)

    val bg by animateColorAsState(
        targetValue = baseBg,
        animationSpec = tween(180),
        label = "railBg"
    )

    val borderColor by animateColorAsState(
        targetValue = if (focused) goldenYellow else goldenYellow.copy(alpha = 0.55f),
        animationSpec = tween(180),
        label = "railBorder"
    )

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.03f else 1f,
        animationSpec = tween(160),
        label = "railScale"
    )

    // ✅ Modern dp-based elevation (bigger when focused)
    val elevation by animateDpAsState(
        targetValue = when {
            isFocused -> 26.dp
            selected -> 14.dp
            else -> 8.dp
        },
        animationSpec = tween(180),
        label = "railElevationDp"
    )

    // ✅ Modern shadow colors (ambient + spot)
    val ambientShadow by animateColorAsState(
        targetValue = Color.Black.copy(
            alpha = when {
                isFocused -> 0.18f
                selected -> 0.14f
                else -> 0.10f
            }
        ),
        animationSpec = tween(180),
        label = "railAmbientShadow"
    )

    val spotShadow by animateColorAsState(
        targetValue = Color.Black.copy(
            alpha = when {
                isFocused -> 0.55f
                selected -> 0.42f
                else -> 0.30f
            }
        ),
        animationSpec = tween(180),
        label = "railSpotShadow"
    )

    val (day, mon) = toDayMonth(event.date)

    Row(
        modifier = modifier
            .height(heightDp.dp)

            // ✅ shadow FIRST (outside), and clip = false so the shadow can extend
            .shadow(
                elevation = elevation,
                shape = shapeC,
                clip = false,
                ambientColor = ambientShadow,
                spotColor = spotShadow
            )

            // your focus scale
            .scale(scale)

            // ✅ clip AFTER shadow (clips content only, not shadow)
            .clip(shapeC)
            .background(bg) // ✅ opaque
            .border(
                width = if (isFocused) 3.dp else 2.dp,
                color = borderColor,
                shape = shapeC
            )
            .onFocusChanged {
                val nowFocused = it.isFocused
                if (nowFocused && !isFocused) onFocused()
                isFocused = nowFocused
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(goldenYellow) // ✅ opaque
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.10f * typeScale,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = sideBarBackground
            )
            Text(
                text = mon,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = MaterialTheme.typography.labelMedium.fontSize * 1.05f * typeScale,
                    fontWeight = FontWeight.SemiBold
                ),
                color = sideBarBackground.copy(alpha = 0.95f)
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
                color = Color.White
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize * 1.05f * typeScale
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White.copy(alpha = 0.85f)
            )
        }

        Spacer(Modifier.width(2.dp))
    }
}

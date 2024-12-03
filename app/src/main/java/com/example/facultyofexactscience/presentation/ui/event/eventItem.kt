package com.example.facultyofexactscience.presentation.ui.event

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.containerColor

@Composable
fun EventItem(
    event: Event,
    isCurrent: Boolean
) {
    val backgroundColor = if (isCurrent) Color(0xFF34693F) else Color.White
    val textColor = if (isCurrent) Color.White else Color(0xFF34693F)
    val borderColor = if (isCurrent) Color(0xFF757D74) else Color(0xFF34693F)
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(14.dp)
    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "08",
                    color = textColor,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Feb",
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier,

                ) {
                Text(
                    text = event.title,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.description,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge,

                    )
            }
        }
    }
}

@Composable
fun EventItemNew(modifier: Modifier = Modifier) {
    Card(
        onClick = {},
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(CardDefaults.VerticalImageAspectRatio),
        border =
        CardDefaults.border(
            focusedBorder = Border(border = BorderStroke(width = 3.dp, color = border), shape = ShapeDefaults.Medium,),
            border = Border(border =BorderStroke(width = 3.dp, color = border), shape = ShapeDefaults.Medium)
        ),
        colors =
        CardDefaults.colors(
            containerColor = containerColor,
            focusedContainerColor = Color.Yellow,
            focusedContentColor = Color.Blue,
            contentColor = Color.White,
        ),
        scale =
        CardDefaults.scale(
            focusedScale = 1.05f,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(14.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "08",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Feb",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Event Title",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Lorem ipsum dolor sit amet, conslit.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        }
    }

}

@Preview
@Composable
private fun Hehehehehhe() {
    EventItemNew()

}
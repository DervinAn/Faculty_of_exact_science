package com.example.facultyofexactscience.faculty.presentation.components.event

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.presentation.FacultyViewModel
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.containerColor
import com.example.facultyofexactscience.ui.theme.contentColor
import com.example.facultyofexactscience.ui.theme.focusedContainerColor
import com.example.facultyofexactscience.ui.theme.focusedContentColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**fun EventItem(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
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
                    modifier = Modifier
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
*/

@Composable
fun EventItemNew(
    event: Event,
    modifier: Modifier = Modifier,
    isSelected: Boolean,

    ) {
    Card(
        onClick = {},
        modifier = modifier
            .width(170.dp)
            .aspectRatio(CardDefaults.VerticalImageAspectRatio)
            .focusable()
            ,
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge,
            ),
            border = Border(
                border = BorderStroke(width = 3.dp, color = border),
                shape = ShapeDefaults.ExtraLarge
            )
        ), colors = CardDefaults.colors(
            containerColor = if (isSelected) Color(0xFF34693F) else containerColor,
            focusedContainerColor = focusedContainerColor,
            contentColor = if (isSelected) Color.White else contentColor,
            focusedContentColor = focusedContentColor
        ),
        scale = CardDefaults.scale(scale = if (isSelected) 1.03f else 1f),
        shape = CardDefaults.shape(ShapeDefaults.ExtraLarge),) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = event.date,
//                        style = MaterialTheme.typography.headlineSmall,,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis, /** Adds "..." at the end if it's too long**/
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Time: "+event.time.startingTime+" "+event.time.endingTime,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis, /** Adds "..." at the end if it's too long**/
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis, /** Adds "..." at the end if it's too long**/
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis, /** Adds "..." at the end if it's too long**/
                    )
                }
            }
        }
    }
}

@Composable
fun Events(viewModel: FacultyViewModel, modifier: Modifier = Modifier) {
    val events by viewModel.events.collectAsState()

    if (events.isEmpty()) return

    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val intervalMillis: Long = 5000

    LaunchedEffect(selectedIndex) {
        while (true) {
            delay(intervalMillis)
            selectedIndex = (selectedIndex + 1) % events.size
            coroutineScope.launch {
                listState.animateScrollToItem(selectedIndex)
            }
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(events) { index, event ->
            EventItemNew(event = event, isSelected = selectedIndex == index)
        }
    }
}


//@Preview
//@Composable
//private fun Hehehehehhe() {
//  /**  EventItemNew(
//        event = Event("Event 1", "Lorem ipsum dolor sit amet, conslit.", "Feb", "21"),
//        modifier = Modifier
//    )*/
//    Events()
//}
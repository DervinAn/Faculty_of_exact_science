package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.faculty.presentation.components.caroussel.Carousel
import com.example.facultyofexactscience.faculty.presentation.components.event.Events
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Time

@Composable
fun Container() {
    val contributorsImage = listOf(
        R.drawable.utmb_last,
        R.drawable.id_logo,
        R.drawable.f_e_s,
    )
    val sampleEvents = listOf(
        Event("Event Title 1", "Description 1", "Dec 5", Time("12", "PM")),
        Event("Event Title 2", "Description 2", "Dec 6", Time("3", "PM")),
        Event("Event Title 3", "Description 3", "Dec 7", Time("6", "PM")),
        Event("Event Title 2", "Description 2", "Dec 6", Time("3", "PM")),
        Event("Event Title 3", "Description 3", "Dec 7", Time("6", "PM"))
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_vectour_wire),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().offset(x=250.dp,y= (-10).dp).clipToBounds(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Carousel(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                , verticalAlignment = Alignment.Bottom
            ) {
                Events(
                    modifier = Modifier.weight(0.9f)
//                    events = sampleEvents,
//                    intervalMillis = 5000
                )
                Contribution(contributorsImage,
                    modifier = Modifier.weight(0.45f)
                )
            }

        }
    }
}



@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1080,
    heightDp = 720
)@Composable
private fun ContainerPrev() {
    Container()
}
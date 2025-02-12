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

@Composable
fun Container() {
    val contributorsImage = listOf(
        R.drawable.utmb_last,
        R.drawable.id_logo,
        R.drawable.f_e_s,
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
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth()
                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                , verticalAlignment = Alignment.Bottom
            ) {
                Events(
                    modifier = Modifier.weight(1f)
                )
                Contribution(contributorsImage)

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
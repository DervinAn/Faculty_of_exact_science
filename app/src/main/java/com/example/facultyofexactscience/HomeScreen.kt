package com.example.facultyofexactscience

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.presentation.ui.Canva
import com.example.facultyofexactscience.presentation.ui.Caroussel.NewCaroussel
import com.example.facultyofexactscience.presentation.ui.Header
import com.example.facultyofexactscience.presentation.ui.Qrcode
import com.example.facultyofexactscience.presentation.ui.event.Eventssss

@Composable
fun Container() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Canva()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header("Faculty of science", R.drawable.f_e_s)
            val items = listOf(
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
            )
            NewCaroussel(
                modifier = Modifier
                    .padding(start = 16.dp,end=16.dp)
                    .height(324.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Eventssss(
                    modifier = Modifier.weight(0.5f)
                )
                Qrcode(
                    modifier = Modifier.weight(0.5f)

                )
            }

        }
    }
}

@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 720
)@Composable
private fun COntainnerPrev() {
    Container()
}
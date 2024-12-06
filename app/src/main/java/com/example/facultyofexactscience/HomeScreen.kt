package com.example.facultyofexactscience

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.presentation.ui.Canva
import com.example.facultyofexactscience.presentation.ui.caroussel.NewCaroussel
import com.example.facultyofexactscience.presentation.ui.Header
import com.example.facultyofexactscience.presentation.ui.Qrcode
import com.example.facultyofexactscience.presentation.ui.event.Eventssss

@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize()
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
            NewCaroussel(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
                    .padding(16.dp),

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
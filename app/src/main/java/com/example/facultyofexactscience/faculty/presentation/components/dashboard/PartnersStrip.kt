// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/PartnersStrip.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.R

@Composable
fun PartnersStrip(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Image(painterResource(R.drawable.utmb_last), null, Modifier.height(40.dp))
        Image(painterResource(R.drawable.id_logo), null, Modifier.height(40.dp))
        Image(painterResource(R.drawable.f_e_s), null, Modifier.height(40.dp))
    }
}
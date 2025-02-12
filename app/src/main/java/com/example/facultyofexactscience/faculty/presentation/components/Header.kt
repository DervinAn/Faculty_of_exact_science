package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@Composable
fun Header(name: String = "",
           icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        //.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Logo",
            modifier = Modifier.size(90.dp)
        )
        Text(
            name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold

        )
    }
}
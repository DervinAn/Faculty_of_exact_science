// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/SocialPanel.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.faculty.presentation.components.Qrcode

@Composable
fun SocialPanel(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Follow us", style = MaterialTheme.typography.titleMedium)
        Qrcode(modifier = Modifier.height(140.dp).fillMaxWidth())
    }
}

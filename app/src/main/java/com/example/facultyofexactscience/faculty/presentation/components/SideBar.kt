package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.ui.theme.sideBarBackground
import com.example.facultyofexactscience.ui.theme.textColor

@Composable
fun Sidebar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.32f)
            .clip(
                RoundedCornerShape(
                    topEnd = 24.dp, bottomEnd = 24.dp
                )
            )
            .background(sideBarBackground)
            .border(
                width = 3.dp, color = border, shape = RoundedCornerShape(
                    topEnd = 24.dp, bottomEnd = 24.dp
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            "UTMB",
            style = MaterialTheme.typography.displaySmall,
            color = textColor,
            fontWeight = FontWeight.Normal
        )
        Text(
            "Exact Science Faculty",
            style = MaterialTheme.typography.headlineSmall,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        TimeDisplay()
        DepartmentMap()
        Quotes(
            modifier = Modifier
        )
        Qrcode(
            modifier = Modifier
        )
    }
}


@Preview(
    name = "TV Preview", showBackground = true, widthDp = 1080, heightDp = 720
)
@Composable
private fun SideBarPrev() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Sidebar()
    }

}
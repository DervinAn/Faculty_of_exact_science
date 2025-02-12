package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.Divider
import com.example.facultyofexactscience.ui.theme.border
import com.example.facultyofexactscience.R

@Composable
fun Sidebar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.3f)
            .clip(
                RoundedCornerShape(
                    topEnd = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .background(Color(0xFFF0FFED))
            .border(
                width = 3.dp, color = border, shape = RoundedCornerShape(
                    topEnd = 24.dp,
                    bottomEnd = 24.dp
                )
            )

    ) {
        Header("UTMB", R.drawable.utmb_last2_rb)
        Divider()
        TimeDisplay()
        DepartmentMap()
        Footer()
    }
}


@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1080,
    heightDp = 720
)
@Composable
private fun SideBarPrev() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Sidebar()
       // Container()
    }

}
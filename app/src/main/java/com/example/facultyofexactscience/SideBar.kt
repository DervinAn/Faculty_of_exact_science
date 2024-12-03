package com.example.facultyofexactscience

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.presentation.ui.DepartmentMap
import com.example.facultyofexactscience.presentation.ui.Footer
import com.example.facultyofexactscience.presentation.ui.Header
import com.example.facultyofexactscience.presentation.ui.TimeDisplay

@Composable
fun Sidebar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.3f)
            .background(
                Color(0xFFF0FFED),
                shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .border(
                width = 2.dp, color = Color(0xffC1C9BE)
            )

    ) {
        Header("UTMB", R.drawable.utmb_last2_rb)
        Divider()
        TimeDisplay()
        DepartmentMap()
        Footer()
    }
}
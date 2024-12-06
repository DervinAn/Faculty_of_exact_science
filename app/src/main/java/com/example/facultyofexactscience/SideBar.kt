package com.example.facultyofexactscience

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.Text
import com.example.facultyofexactscience.presentation.ui.DepartmentMap
import com.example.facultyofexactscience.presentation.ui.Footer
import com.example.facultyofexactscience.presentation.ui.Header
import com.example.facultyofexactscience.presentation.ui.TimeDisplay
import com.example.facultyofexactscience.ui.theme.border

@Composable
fun Sidebar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.3f)
            .clip(
                RoundedCornerShape(
                topEnd = 22.dp,
                bottomEnd = 22.dp
            ))
            .background(Color(0xFFF0FFED),)
            .border(
                width = 2.dp, color = border, shape = RoundedCornerShape(
                    topEnd = 23.dp,
                    bottomEnd = 23.dp
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
package com.example.facultyofexactscience.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.ui.theme.border

@Composable
fun Footer() {
    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.55f)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, border, shape = RoundedCornerShape(16.dp))
                .background(Color(0xFFD3E8D1))

        ) {
            Text(
                "Lorem Epsun Lorem EpsunLoremEpsun",
                fontSize = 28.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.id_logo),
            contentDescription = "ID",
            modifier = Modifier.size(80.dp),
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
private fun hhh() {
    Footer()
    
}
package com.example.facultyofexactscience

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.Text

@Composable
fun Quotes(modifier: Modifier = Modifier) {
    Card (
        {},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.55f)
            .background(Color(0xFFD3E8D1))

    ) {
        Text(
            "Lorem Epsun Lorem EpsunLoremEpsun",
            fontSize = 28.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun QuotesPrev() {
    Quotes()

}
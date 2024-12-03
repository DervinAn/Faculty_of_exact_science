package com.example.facultyofexactscience.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.example.facultyofexactscience.R

@Composable
fun Qrcode(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Social Media",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(ShapeDefaults.Medium)
                .background(Color(0xFF34693F))
                .padding(8.dp)
            ,
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .clip(ShapeDefaults.Small)
                    .background(Color.White)
                    .padding(8.dp)
            ) {
            Image(
                painter = painterResource(id = R.drawable.qrcode_s_e),
                contentDescription = "QR",
                modifier = Modifier.fillMaxSize()
            )
            }
        }
    }
}

@Preview
@Composable
private fun QrCodePrev() {

    Qrcode()
}
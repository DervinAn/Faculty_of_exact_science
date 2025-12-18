package com.example.facultyofexactscience.core.presentation.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.facultyofexactscience.R

@Composable
fun PartnerChip(
    modifier: Modifier = Modifier,
    chipSize: Dp = 72.dp, // same tone you used
    logoRes: Int,

    ) {
    Color(0xA6FFFCFC)
    Box(
        modifier = modifier.size(chipSize)
        //.background(cardColor)
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(logoRes),
            contentDescription = "PartnerChip"
        )

    }


}

@Composable
fun PartnerRow(
    modifier: Modifier = Modifier,
    chipSize: Dp = 72.dp,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PartnerChip(logoRes = R.drawable.utmb, chipSize = chipSize)
        PartnerChip(logoRes = R.drawable.utmb_f_s_e, chipSize = chipSize)
        PartnerChip(logoRes = R.drawable.idev, chipSize = chipSize)
    }
}
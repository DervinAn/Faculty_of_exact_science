package com.example.facultyofexactscience.core.presentation.components.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
    chipSize: Dp = 72.dp,
    logoRes: Int,
) {
    Surface(
        modifier = modifier.size(chipSize),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.72f),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.35f)),
        shadowElevation = 6.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(logoRes),
                contentDescription = "Partner",
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}

@Composable
fun PartnerRow(
    modifier: Modifier = Modifier,
    chipSize: Dp = 72.dp,
) {
    Row(
        modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PartnerChip(logoRes = R.drawable.utmb, chipSize = chipSize)
        PartnerChip(logoRes = R.drawable.utmb_f_s_e, chipSize = chipSize)
        PartnerChip(logoRes = R.drawable.idev, chipSize = chipSize)
    }
}

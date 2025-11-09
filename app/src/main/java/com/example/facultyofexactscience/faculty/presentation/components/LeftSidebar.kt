// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/components/LeftSidebar.kt
package com.example.facultyofexactscience.faculty.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.ui.theme.sideBarBackground
import com.example.facultyofexactscience.ui.theme.textColor

@Composable
fun LeftSidebar(
    quote: String,
    modifier: Modifier = Modifier,
    cornerDp: Float,
    gutterDp: Float,
    @DrawableRes illustrationRes: Int = R.drawable.map_pic
) {
    Column(modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = cornerDp.dp,
                    bottomStart = cornerDp.dp))
            .background(sideBarBackground)
        .padding(gutterDp.dp)
        .padding(vertical = 8
            .dp),
        verticalArrangement = Arrangement
            .SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
//        Text(
//            text = "UTMB",
//            style = MaterialTheme.typography.displaySmall,
//            color = textColor,
//            textAlign = TextAlign.Center
//        )
        Text(
            text = "Faculty of Science Exact",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        // Live time + date
        TimeDisplay()

        // Illustration card (replace with your asset or remote URL)
//        Image(
//            painter = painterResource(illustrationRes),
//            contentDescription = "Announcement illustration",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(min = 140.dp, max = 180.dp)
//                .clip(RoundedCornerShape((cornerDp - 6).dp))
//                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.90f))
//        )

        // Quote
        QuoteCard(quote = quote)

        // Social + QR block
        SocialQrPanel(Modifier.fillMaxWidth())
    }
}

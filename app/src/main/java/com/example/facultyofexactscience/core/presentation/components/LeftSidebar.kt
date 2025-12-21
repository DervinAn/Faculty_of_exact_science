// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/components/LeftSidebar.kt
package com.example.facultyofexactscience.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.core.presentation.components.ui.theme.goldenYellow
import com.example.facultyofexactscience.core.presentation.components.ui.theme.sideBarBackground
import com.example.facultyofexactscience.core.presentation.components.ui.theme.textColor

@Composable
fun LeftSidebar(
    quote: String,
    modifier: Modifier = Modifier,
    cornerDp: Float,
    gutterDp: Float,
    @DrawableRes illustrationRes: Int = R.drawable.map_pic,
) {
    val cardShape = RoundedCornerShape(
        topStart = cornerDp.dp,
        bottomStart = cornerDp.dp
    )

    Column(
        modifier = modifier
            .clip(cardShape)
            .background(sideBarBackground)
            .padding(gutterDp.dp)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SidebarHeader()
        Spacer(Modifier.height(14.dp))
        HorizontalDivider(
            modifier = Modifier.padding(
                horizontal = 8.dp
            ),
            color = Color(0x66FFFFFF)
        )
        Spacer(Modifier.height(14.dp))

        TimeDisplay()

        // Spacer(Modifier.height(16.dp))

//        SidebarIllustration(
//            illustrationRes = illustrationRes,
//            modifier = Modifier.fillMaxWidth()
//        )

        Spacer(Modifier.height(16.dp))
        SidebarQuoteCard(
            quote = quote,
            modifier = Modifier.fillMaxWidth()
        )

        // pushes the Social/QR block to the bottom like your mock
        Spacer(Modifier.weight(1f))

        SocialQrPanel(
            facebookLabel = "Facebook",
            websiteLabel = "Website",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SidebarHeader(
    title: String = "UTMB",
    subtitle: String = "Exact Science Faculty",
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SidebarIllustration(
    @DrawableRes illustrationRes: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFD7E9EF)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(illustrationRes),
            contentDescription = "Sidebar illustration",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}

// Existing file: replace your current SidebarQuoteCard() with this refactor (no new file)

@Composable
private fun SidebarQuoteCard(
    quote: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF0B6B56), // keep your green
    accentColor: Color = goldenYellow,          // keep your yellow
) {
    val shape = RoundedCornerShape(22.dp)

    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        shadowElevation = 10.dp,
        tonalElevation = 0.dp,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.10f))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Quote mark (kept in the same color accent, better sizing/spacing)

            Text(
                text = quote.trim(),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.sp
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 6.dp)
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.30f),
                thickness = 1.dp
            )
            Icon(painterResource(R.drawable.quotes),
                tint = Color.White,
                contentDescription = null)
        }
    }
}


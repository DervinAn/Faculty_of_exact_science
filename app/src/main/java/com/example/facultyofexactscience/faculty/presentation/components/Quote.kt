package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.facultyofexactscience.R
import com.example.facultyofexactscience.faculty.presentation.FacultyViewModel
import com.example.facultyofexactscience.ui.theme.quoteCardColor
import com.example.facultyofexactscience.ui.theme.quoteIconColor
import com.example.facultyofexactscience.ui.theme.textColor
import kotlinx.coroutines.delay

@Composable
fun Quotes(viewModel: FacultyViewModel) {
    val quotes by viewModel.quotes.collectAsState()

    if (quotes.isEmpty()) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading quote...", color = textColor)
        }
        return
    }

    // State to track the current index
    var currentIndex by remember { mutableIntStateOf(0) }

    // Launch a coroutine that updates the index every 10 seconds
    LaunchedEffect(quotes) {
        while (true) {
            delay(10_000L)
            currentIndex = (currentIndex + 1) % quotes.size
        }
    }

    val quote = quotes[currentIndex]

    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .background(quoteCardColor)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.quote),
            contentDescription = null,
            tint = quoteIconColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = quote.text,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QuoteCard(quote: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(quoteCardColor)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(R.drawable.quote),
            contentDescription = null,
            tint = quoteIconColor,
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = quote,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Start
        )
    }
}

package com.example.facultyofexactscience.faculty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.runtime.getValue
import com.example.facultyofexactscience.ui.theme.quoteIconColor
import com.example.facultyofexactscience.ui.theme.textColor

@Composable
fun Quotes(viewModel: FacultyViewModel) {
    val quotes by viewModel.quotes.collectAsState()

    val quote = quotes.firstOrNull() ?: return

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


//@Preview
//@Composable
//private fun QuotesPrev() {
//    Quotes()
//
//}
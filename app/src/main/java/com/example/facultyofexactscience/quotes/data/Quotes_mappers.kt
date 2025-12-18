package com.example.facultyofexactscience.quotes.data

import com.example.facultyofexactscience.quotes.domain.Quotes

fun QuoteDto.toDomain(): Quotes {
    return Quotes(
        id = id?.toString() ?: "",
        text = this.description // ✅ use DTO field
    )
}

fun Quotes.toDto(): QuoteDto {
    return QuoteDto(
        id = id.toLongOrNull(),
        description = text
    )
}

package com.example.facultyofexactscience.feature.faculty.data.remote.dto

import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Time
import com.example.facultyofexactscience.faculty.domain.Quotes

fun EventDto.toDomain(): Event =
    Event(
        title = title,
        description = description,
        date = date,
        time = Time(startingTime = startTime, endingTime = endTime),
        isCurrent = false
    )

fun Event.toDto(id: String? = null): EventDto =
    EventDto(
        id = id,
        title = title,
        description = description,
        date = date,
        startTime = time.startingTime,
        endTime = time.endingTime
    )

fun QuoteDto.toDomain(): Quotes =
    Quotes(
        id = id.orEmpty(),
        text = description
    )

fun Quotes.toDto(): QuoteDto =
    QuoteDto(
        id = id.ifBlank { null },
        description = text
    )

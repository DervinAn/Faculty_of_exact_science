// app/src/main/java/com/example/facultyofexactscience/feature/faculty/data/remote/dto/Mappers.kt
package com.example.facultyofexactscience.faculty.data.remote.dto

import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.example.facultyofexactscience.faculty.domain.Time

fun QuoteDto.toDomain() = Quotes(
    id = id?.toString().orEmpty(),   // domain keeps String id if you prefer
    text = description
)

fun Quotes.toDto() = QuoteDto(
    id = id.toLongOrNull(),          // send back a number if id is numeric; null for create
    description = text
)
fun EventDto.toDomain(): Event = Event(
    title = title,
    description = description,
    date = date,
    time = Time(startTime, endTime),
    isCurrent = false
)

fun Event.toDto(id: String? = null): EventDto = EventDto(
    id = id?.toLongOrNull(), // ok to be null on create
    title = title,
    description = description,
    date = date,
    startTime = time.startingTime,
    endTime = time.endingTime
)
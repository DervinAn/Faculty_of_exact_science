package com.example.facultyofexactscience.events.data

import com.example.facultyofexactscience.core.data.networking.toStorageUrl
import com.example.facultyofexactscience.events.domain.Event
import com.example.facultyofexactscience.events.domain.Time

fun EventDto.toDomain(): Event = Event(
    id = id,
    title = title,
    description = description,
    date = date,
    time = Time(startTime, endTime),
    images = images.filter { it.isNotBlank() }.map { it.toStorageUrl() },
    isCurrent = false
)

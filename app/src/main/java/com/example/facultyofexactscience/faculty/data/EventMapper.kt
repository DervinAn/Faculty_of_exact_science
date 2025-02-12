package com.example.facultyofexactscience.faculty.data

import com.example.facultyofexactscience.faculty.data.dto.EventDto
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Time


fun EventDto.toEvent(): Event {
    return Event(
        title = title,
        description = description,
        date = date,
        time = Time(
            startingTime = time.startingTime,
            endingTime = time.endingTime
        )
    )

}
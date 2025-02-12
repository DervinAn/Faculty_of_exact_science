package com.example.facultyofexactscience.faculty.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TimeDto (
    val startingTime: String,
    val endingTime: String
)

@Serializable
data class EventDto (
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: TimeDto
)
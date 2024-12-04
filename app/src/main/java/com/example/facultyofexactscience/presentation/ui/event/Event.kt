package com.example.facultyofexactscience.presentation.ui.event

data class Time(
    val startingTime: String = "",
    val endingTime: String = ""
)

data class Event(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: Time = Time("", ""),
    var isCurrent: Boolean = false
)
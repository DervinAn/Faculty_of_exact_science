package com.example.facultyofexactscience.events.domain

data class Time(
    val startingTime: String = "",
    val endingTime: String = "",
)

data class Event(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val date: String? = "",
    val time: Time = Time("", ""),

    // ✅ full URLs ready for Coil
    val images: List<String> = emptyList(),

    var isCurrent: Boolean = false,
)

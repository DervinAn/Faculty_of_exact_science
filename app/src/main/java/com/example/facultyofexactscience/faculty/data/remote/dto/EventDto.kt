package com.example.facultyofexactscience.faculty.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class EventDto(
    @SerialName("id")         val id: Long? = null,     // <- was String?; API sends numbers
    @SerialName("title")      val title: String,
    @SerialName("description")val description: String,
    @SerialName("date")       val date: String,         // e.g., "2025-02-01"
    @SerialName("start_time") val startTime: String,    // e.g., "09:00"
    @SerialName("end_time")   val endTime: String       // e.g., "12:00"
)

package com.example.facultyofexactscience.feature.faculty.data.remote.dto
import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class EventDto(
    val id: String? = null,
    val title: String,
    val description: String,
    val date: String,
    @SerialName("start_time") val startTime: String,
    @SerialName("end_time") val endTime: String
)

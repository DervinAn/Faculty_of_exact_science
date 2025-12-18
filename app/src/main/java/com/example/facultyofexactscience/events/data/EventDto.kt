package com.example.facultyofexactscience.events.data

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class EventDto(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",

    @SerialName("date_")
    val date: String? = null,

    @SerialName("start_time")
    val startTime: String = "",

    @SerialName("end_time")
    val endTime: String = "",

    // ✅ relative paths from API
    val images: List<String> = emptyList(),

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null,
)

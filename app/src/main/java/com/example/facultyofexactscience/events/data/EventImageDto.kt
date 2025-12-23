package com.example.facultyofexactscience.events.data

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class EventImageDto(
    val url: String,
)
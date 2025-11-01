package com.example.facultyofexactscience.faculty.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class EventImageDto(
    val url: String
)
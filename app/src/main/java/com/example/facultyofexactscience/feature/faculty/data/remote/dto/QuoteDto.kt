package com.example.facultyofexactscience.feature.faculty.data.remote.dto
import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class QuoteDto(
    val id: String? = null,
    val description: String
)

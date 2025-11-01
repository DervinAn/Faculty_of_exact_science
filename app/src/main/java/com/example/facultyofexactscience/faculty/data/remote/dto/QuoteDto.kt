package com.example.facultyofexactscience.faculty.data.remote.dto
import android.annotation.SuppressLint
import kotlinx.serialization.Serializable



@Serializable
data class QuoteDto(
    val id: Long? = null,          // <- was String?; API returns numbers
    val description: String
)
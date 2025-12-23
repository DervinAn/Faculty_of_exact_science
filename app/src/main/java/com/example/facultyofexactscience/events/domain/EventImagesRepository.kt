package com.example.facultyofexactscience.events.domain

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result

interface EventImagesRepository {
    suspend fun getAll(): Result<List<String>, NetworkError>
}

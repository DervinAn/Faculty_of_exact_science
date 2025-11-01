package com.example.facultyofexactscience.faculty.domain.repository

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result

interface EventImagesRepository {
    suspend fun getAll(): Result<List<String>, NetworkError>
}

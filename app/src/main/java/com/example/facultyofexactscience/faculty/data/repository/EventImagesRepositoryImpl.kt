package com.example.facultyofexactscience.faculty.data.repository

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.faculty.domain.repository.EventImagesRepository

class EventImagesRepositoryImpl(
    private val api: FacultyApi
) : EventImagesRepository {
    override suspend fun getAll(): Result<List<String>, NetworkError> =
        api.getAllEventImages()
}

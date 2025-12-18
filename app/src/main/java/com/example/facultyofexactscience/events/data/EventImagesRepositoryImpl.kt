package com.example.facultyofexactscience.events.data

import com.example.facultyofexactscience.core.data.networking.toStorageUrl
import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.events.domain.EventImagesRepository

class EventImagesRepositoryImpl(
    private val api: FacultyApi,
) : EventImagesRepository {

    override suspend fun getAll(): Result<List<String>, NetworkError> =
        api.getEvents().map { dtos ->
            dtos.flatMap { it.images }
                .filter { it.isNotBlank() }
                .distinct()
                .map { it.toStorageUrl() }
        }
}

package com.example.facultyofexactscience.feature.faculty.data.repository

import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.asEmptyDataResult
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.feature.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.toDomain
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.toDto
import com.example.facultyofexactscience.feature.faculty.domain.repository.EventsRepository
import com.example.facultyofexactscience.faculty.domain.Event

class EventsRepositoryImpl(
    private val api: FacultyApi
) : EventsRepository {

    override suspend fun getAll(): Result<List<Event>, NetworkError> =
        api.getEvents().map { it.map { dto -> dto.toDomain() } }

    override suspend fun get(id: String): Result<Event, NetworkError> =
        api.getEvent(id).map { it.toDomain() }

    override suspend fun create(event: Event): Result<Event, NetworkError> =
        api.createEvent(event.toDto()).map { it.toDomain() }

    override suspend fun update(id: String, event: Event): Result<Event, NetworkError> =
        api.updateEvent(id, event.toDto(id)).map { it.toDomain() }

    override suspend fun delete(id: String): EmptyResult<NetworkError> =
        api.deleteEvent(id).asEmptyDataResult()
}

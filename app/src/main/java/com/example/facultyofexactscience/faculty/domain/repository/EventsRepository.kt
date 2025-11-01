package com.example.facultyofexactscience.faculty.domain.repository

import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.domain.Event

interface EventsRepository {
    suspend fun getAll(): Result<List<Event>, NetworkError>
    suspend fun get(id: String): Result<Event, NetworkError>
    suspend fun create(event: Event): Result<Event, NetworkError>
    suspend fun update(id: String, event: Event): Result<Event, NetworkError>
    suspend fun delete(id: String): EmptyResult<NetworkError>
}

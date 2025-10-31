package com.example.facultyofexactscience.feature.faculty.data.remote.service

import com.example.facultyofexactscience.core.data.networking.constructUrl
import com.example.facultyofexactscience.core.data.networking.safeCall
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.feature.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.EventDto
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class FacultyApiImpl(
    private val client: HttpClient
) : FacultyApi {

    override suspend fun getEvents(): Result<List<EventDto>, NetworkError> =
        safeCall { client.get(constructUrl("/events")) }

    override suspend fun getEvent(id: String): Result<EventDto, NetworkError> =
        safeCall { client.get(constructUrl("/events/$id")) }

    override suspend fun createEvent(dto: EventDto): Result<EventDto, NetworkError> =
        safeCall {
            client.post(constructUrl("/events")) { setBody(dto) }
        }

    override suspend fun updateEvent(id: String, dto: EventDto): Result<EventDto, NetworkError> =
        safeCall {
            client.put(constructUrl("/events/$id")) { setBody(dto) }
        }

    override suspend fun deleteEvent(id: String): Result<Unit, NetworkError> =
        safeCall { client.delete(constructUrl("/events/$id")) }

    // Quotes
    override suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError> =
        safeCall { client.get(constructUrl("/quotes")) }

    override suspend fun getQuote(id: String): Result<QuoteDto, NetworkError> =
        safeCall { client.get(constructUrl("/quotes/$id")) }

    override suspend fun createQuote(dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.post(constructUrl("/quotes")) { setBody(dto) }
        }

    override suspend fun updateQuote(id: String, dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.put(constructUrl("/quotes/$id")) { setBody(dto) }
        }

    override suspend fun deleteQuote(id: String): Result<Unit, NetworkError> =
        safeCall { client.delete(constructUrl("/quotes/$id")) }
}

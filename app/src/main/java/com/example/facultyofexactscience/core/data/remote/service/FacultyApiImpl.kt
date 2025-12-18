package com.example.facultyofexactscience.core.data.remote.service

import com.example.facultyofexactscience.core.data.networking.constructUrl
import com.example.facultyofexactscience.core.data.networking.safeCall
import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.events.data.EventDto
import com.example.facultyofexactscience.quotes.data.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class FacultyApiImpl(
    private val client: HttpClient,
) : FacultyApi {

    // ✅ GET /quotes
    override suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError> =
        safeCall { client.get(constructUrl("/quotes")) }

    // kept
    override suspend fun createQuote(dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.post(constructUrl("/quotes")) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
        }

    override suspend fun getQuote(id: String): Result<QuoteDto, NetworkError> =
        safeCall { client.get(constructUrl("/quotes/$id")) }

    override suspend fun updateQuote(id: String, dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.put(constructUrl("/quotes/$id")) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
        }

    override suspend fun deleteQuote(id: String): Result<Unit, NetworkError> =
        safeCall { client.delete(constructUrl("/quotes/$id")) }

    // ✅ GET /events (READ only)
    override suspend fun getEvents(): Result<List<EventDto>, NetworkError> =
        safeCall { client.get(constructUrl("/events")) }
}

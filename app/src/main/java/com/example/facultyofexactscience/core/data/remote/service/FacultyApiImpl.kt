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
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class FacultyApiImpl(
    private val client: HttpClient,
) : FacultyApi {

    // ✅ GET /quotes
    override suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError> = safeCall {
        client.get(constructUrl("/quotes")) {
            header("Cache-Control", "no-cache")
        }
    }
    override suspend fun getEvents(): Result<List<EventDto>, NetworkError> =
        safeCall {
            client.get(constructUrl("/events")) {
                header("Cache-Control", "no-store, no-cache, max-age=0")
                header("Pragma", "no-cache")
            }
        }

}

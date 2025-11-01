// app/src/main/java/com/example/facultyofexactscience/feature/faculty/data/remote/service/FacultyApiImpl.kt
package com.example.facultyofexactscience.faculty.data.remote.service

import com.example.facultyofexactscience.core.data.networking.constructUrl
import com.example.facultyofexactscience.core.data.networking.safeCall
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.faculty.data.remote.dto.EventDto
import com.example.facultyofexactscience.faculty.data.remote.dto.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.*
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

class FacultyApiImpl(
    private val client: HttpClient
) : FacultyApi {

    // ---------- QUOTES ----------

    override suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError> =
        safeCall {
            client.get(constructUrl("/quotes/"))
        }

    override suspend fun createQuote(dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.post(constructUrl("/quotes/")) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
        }

    override suspend fun getQuote(id: String): Result<QuoteDto, NetworkError> =
        safeCall {
            client.get(constructUrl("/quotes/$id"))
        }

    override suspend fun updateQuote(id: String, dto: QuoteDto): Result<QuoteDto, NetworkError> =
        safeCall {
            client.put(constructUrl("/quotes/$id")) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
        }

    override suspend fun deleteQuote(id: String): Result<Unit, NetworkError> =
        safeCall {
            client.delete(constructUrl("/quotes/$id"))
        }

    // ------ EVENT IMAGES --------

    override suspend fun getAllEventImages(): Result<List<String>, NetworkError> =
        safeCall {
            client.get(constructUrl("/events_images/"))
        }

    @OptIn(InternalAPI::class)
    override suspend fun addEventImage(eventId: String, bytes: ByteArray, filename: String): Result<Unit, NetworkError> =
        safeCall {
            client.submitFormWithBinaryData(
                url = constructUrl("/events_images/$eventId"),
                formData = formData {
                    append(
                        key = "file",
                        value = bytes,
                        headers = Headers.build {
                            append(io.ktor.http.HttpHeaders.ContentType, ContentType.Image.Any)
                            append(
                                io.ktor.http.HttpHeaders.ContentDisposition,
                                ContentDisposition.File
                                    .withParameter(ContentDisposition.Parameters.Name, "file")
                                    .withParameter(ContentDisposition.Parameters.FileName, filename)
                                    .toString()
                            )
                        }
                    )
                }
            )
        }

    override suspend fun deleteEventImage(eventId: String): Result<Unit, NetworkError> =
        safeCall {
            client.delete(constructUrl("/events_images/$eventId"))
        }

    // -----------------------------
// EVENTS ENDPOINTS
// -----------------------------

    override suspend fun getEvents(): Result<List<EventDto>, NetworkError> = safeCall {
        client.get { url(constructUrl("/events/")) }
    }

    override suspend fun createEvent(dto: EventDto): Result<EventDto, NetworkError> = safeCall {
        client.post {
            url(constructUrl("/events/"))
            contentType(ContentType.Application.Json)
            setBody(dto)
        }
    }

    override suspend fun getEvent(id: String): Result<EventDto, NetworkError> = safeCall {
        client.get { url(constructUrl("/events/$id")) }
    }

    override suspend fun updateEvent(id: String, dto: EventDto): Result<EventDto, NetworkError> = safeCall {
        client.put {
            url(constructUrl("/events/$id"))
            contentType(ContentType.Application.Json)
            setBody(dto)
        }
    }

    override suspend fun deleteEvent(id: String): Result<Unit, NetworkError> = safeCall {
        client.delete { url(constructUrl("/events/$id")) }
    }



}

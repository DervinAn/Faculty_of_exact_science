// app/src/main/java/com/example/facultyofexactscience/feature/faculty/data/remote/api/FacultyApi.kt
package com.example.facultyofexactscience.faculty.data.remote.api

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.data.remote.dto.EventDto
import com.example.facultyofexactscience.faculty.data.remote.dto.QuoteDto

interface FacultyApi {
    // QUOTES
    suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError>       // GET /quotes/
    suspend fun createQuote(dto: QuoteDto): Result<QuoteDto, NetworkError> // POST /quotes/
    suspend fun getQuote(id: String): Result<QuoteDto, NetworkError>    // GET /quotes/{id}
    suspend fun updateQuote(id: String, dto: QuoteDto): Result<QuoteDto, NetworkError> // PUT /quotes/{id}
    suspend fun deleteQuote(id: String): Result<Unit, NetworkError>     // DELETE /quotes/{id}

    // EVENT IMAGES (kept simple for now)
    suspend fun getAllEventImages(): Result<List<String>, NetworkError> // GET /events_images/
    suspend fun addEventImage(eventId: String, bytes: ByteArray, filename: String): Result<Unit, NetworkError> // POST /events_images/{id}
    suspend fun deleteEventImage(eventId: String): Result<Unit, NetworkError> // DELETE /events_images/{id}

    suspend fun getEvents(): Result<List<EventDto>, NetworkError>
    suspend fun createEvent(dto: EventDto): Result<EventDto, NetworkError>
    suspend fun getEvent(id: String): Result<EventDto, NetworkError>
    suspend fun updateEvent(id: String, dto: EventDto): Result<EventDto, NetworkError>
    suspend fun deleteEvent(id: String): Result<Unit, NetworkError>
}

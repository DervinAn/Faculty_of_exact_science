package com.example.facultyofexactscience.feature.faculty.data.remote.api

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.EventDto
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.QuoteDto

interface FacultyApi {
    // Events
    suspend fun getEvents(): Result<List<EventDto>, NetworkError>
    suspend fun getEvent(id: String): Result<EventDto, NetworkError>
    suspend fun createEvent(dto: EventDto): Result<EventDto, NetworkError>
    suspend fun updateEvent(id: String, dto: EventDto): Result<EventDto, NetworkError>
    suspend fun deleteEvent(id: String): Result<Unit, NetworkError>

    // Quotes
    suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError>
    suspend fun getQuote(id: String): Result<QuoteDto, NetworkError>
    suspend fun createQuote(dto: QuoteDto): Result<QuoteDto, NetworkError>
    suspend fun updateQuote(id: String, dto: QuoteDto): Result<QuoteDto, NetworkError>
    suspend fun deleteQuote(id: String): Result<Unit, NetworkError>
}

package com.example.facultyofexactscience.core.data.remote.api

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.events.data.EventDto
import com.example.facultyofexactscience.quotes.data.QuoteDto

interface FacultyApi {
    // QUOTES (kept)
    suspend fun getQuotes(): Result<List<QuoteDto>, NetworkError>


    // EVENTS (TV app uses READ only)
    suspend fun getEvents(): Result<List<EventDto>, NetworkError>
}

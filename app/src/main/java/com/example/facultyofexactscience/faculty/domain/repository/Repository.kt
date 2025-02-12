package com.example.facultyofexactscience.faculty.domain.repository

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.example.facultyofexactscience.faculty.domain.Event

interface Repository {
    suspend fun getEvents(): Result<List<Event>, NetworkError>
    suspend fun getQuotes(): Result<List<Quotes>, NetworkError>
}
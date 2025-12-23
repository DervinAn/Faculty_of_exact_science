// app/src/main/java/com/example/facultyofexactscience/feature/faculty/domain/repository/QuotesRepository.kt
package com.example.facultyofexactscience.quotes.domain

import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result

interface QuotesRepository {
    suspend fun getAll(forceRefresh: Boolean = false): Result<List<Quotes>, NetworkError>
}

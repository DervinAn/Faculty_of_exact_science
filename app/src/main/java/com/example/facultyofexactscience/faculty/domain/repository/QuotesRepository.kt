// app/src/main/java/com/example/facultyofexactscience/feature/faculty/domain/repository/QuotesRepository.kt
package com.example.facultyofexactscience.faculty.domain.repository

import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.domain.Quotes

interface QuotesRepository {
    suspend fun getAll(): Result<List<Quotes>, NetworkError>
    suspend fun get(id: String): Result<Quotes, NetworkError>
    suspend fun create(quote: Quotes): Result<Quotes, NetworkError>
    suspend fun update(id: String, quote: Quotes): Result<Quotes, NetworkError>
    suspend fun delete(id: String): EmptyResult<NetworkError>
}

// app/src/main/java/com/example/facultyofexactscience/feature/faculty/data/repository/QuotesRepositoryImpl.kt
package com.example.facultyofexactscience.quotes.data

import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.asEmptyDataResult
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.quotes.domain.Quotes
import com.example.facultyofexactscience.quotes.domain.QuotesRepository

class QuotesRepositoryImpl(
    private val api: FacultyApi,
) : QuotesRepository {

    override suspend fun getAll(): Result<List<Quotes>, NetworkError> =
        api.getQuotes().map { list -> list.map { it.toDomain() } }

    override suspend fun get(id: String): Result<Quotes, NetworkError> =
        api.getQuote(id).map { it.toDomain() }

    override suspend fun create(quote: Quotes): Result<Quotes, NetworkError> =
        api.createQuote(quote.toDto()).map { it.toDomain() }

    override suspend fun update(id: String, quote: Quotes): Result<Quotes, NetworkError> =
        api.updateQuote(id, quote.toDto()).map { it.toDomain() }

    override suspend fun delete(id: String): EmptyResult<NetworkError> =
        api.deleteQuote(id).asEmptyDataResult()
}

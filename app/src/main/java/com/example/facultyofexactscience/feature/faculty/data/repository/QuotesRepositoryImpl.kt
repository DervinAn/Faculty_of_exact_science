package com.example.facultyofexactscience.feature.faculty.data.repository

import com.example.facultyofexactscience.core.domain.util.EmptyResult
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.asEmptyDataResult
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.feature.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.toDomain
import com.example.facultyofexactscience.feature.faculty.data.remote.dto.toDto
import com.example.facultyofexactscience.feature.faculty.domain.repository.QuotesRepository
import com.example.facultyofexactscience.faculty.domain.Quotes

class QuotesRepositoryImpl(
    private val api: FacultyApi
) : QuotesRepository {

    override suspend fun getAll(): Result<List<Quotes>, NetworkError> =
        api.getQuotes().map { it.map { dto -> dto.toDomain() } }

    override suspend fun get(id: String): Result<Quotes, NetworkError> =
        api.getQuote(id).map { it.toDomain() }

    override suspend fun create(quote: Quotes): Result<Quotes, NetworkError> =
        api.createQuote(quote.toDto()).map { it.toDomain() }

    override suspend fun update(id: String, quote: Quotes): Result<Quotes, NetworkError> =
        api.updateQuote(id, quote.toDto()).map { it.toDomain() }

    override suspend fun delete(id: String): EmptyResult<NetworkError> =
        api.deleteQuote(id).asEmptyDataResult()
}

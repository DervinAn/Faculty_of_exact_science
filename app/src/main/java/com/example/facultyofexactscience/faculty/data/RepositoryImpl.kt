package com.example.facultyofexactscience.faculty.data


import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.example.facultyofexactscience.faculty.domain.repository.Repository
import com.example.facultyofexactscience.faculty.domain.Event

class RepositoryImpl(
//    private val httpClient: HttpClientFactory,
): Repository {
    override suspend fun getEvents(): Result<List<Event>, NetworkError> {
        return Result.Success(emptyList())
//        safeCall {
//            httpClient.get(
//                urlString = constructUrl("/assets")
//            )
//            .map { response ->
//                response.data.map { it.    }
//            }
        }

    override suspend fun getQuotes(): Result<List<Quotes>, NetworkError> {
        TODO("Not yet implemented")
    }
}
package com.example.facultyofexactscience.quotes.data

import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.quotes.domain.Quotes
import com.example.facultyofexactscience.quotes.domain.QuotesRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class QuotesRepositoryImpl(
    private val api: FacultyApi
) : QuotesRepository {

    private val mutex = Mutex()
    private var cache: List<Quotes> = emptyList()
    private var lastFetchMs: Long = 0L

    // adjust as you like
    private val cacheTtlMs: Long = 30_000L // 30 seconds

    override suspend fun getAll(forceRefresh: Boolean): Result<List<Quotes>, NetworkError> =
        mutex.withLock {
            android.util.Log.d("QuotesRepositoryImpl", "Fetching quotes from network (forceRefresh=$forceRefresh)")
            val now = System.currentTimeMillis()

            val cacheValid = cache.isNotEmpty() && (now - lastFetchMs) < cacheTtlMs
            if (!forceRefresh && cacheValid) {
                return Result.Success(cache)
            }

            val res = api.getQuotes().map { dtos ->
                dtos.map { it.toDomain() }
            }

            if (res is Result.Success) {
                cache = res.data
                lastFetchMs = now
            }
            res
        }
}

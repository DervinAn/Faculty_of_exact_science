package com.example.facultyofexactscience.events.data

import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.core.domain.util.map
import com.example.facultyofexactscience.events.domain.Event
import com.example.facultyofexactscience.events.domain.EventsRepository
import java.time.LocalDate
import java.time.LocalTime

class EventsRepositoryImpl(
    private val api: FacultyApi,
) : EventsRepository {

    override suspend fun getAll(): Result<List<Event>, NetworkError> =
        api.getEvents().map { dtos ->
            val today = LocalDate.now()
            dtos.map { it.toDomain() }
                .filter { it.date.safeParseDate() >= today }
                .sortedWith(
                    compareBy<Event>(
                        { it.date.safeParseDate() },
                        { it.time.startingTime.safeParseTime() }
                    )
                )
        }

    // TV only (keep interface but disable writes)
    override suspend fun get(id: String) = Result.Error(NetworkError.UNKNOWN)
    override suspend fun create(event: Event) = Result.Error(NetworkError.UNKNOWN)
    override suspend fun update(id: String, event: Event) = Result.Error(NetworkError.UNKNOWN)
    override suspend fun delete(id: String) =
        Result.Error(NetworkError.UNKNOWN)
}

private fun String?.safeParseDate(): LocalDate =
    runCatching { if (this.isNullOrBlank()) LocalDate.MIN else LocalDate.parse(this) }
        .getOrDefault(LocalDate.MIN)

private fun String?.safeParseTime(): LocalTime =
    runCatching { if (this.isNullOrBlank()) LocalTime.MIN else LocalTime.parse(this.take(8)) }
        .getOrDefault(LocalTime.MIN)

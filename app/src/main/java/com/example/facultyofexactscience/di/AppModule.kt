package com.example.facultyofexactscience.di

import com.example.facultyofexactscience.core.data.networking.HttpClientFactory
import com.example.facultyofexactscience.feature.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.feature.faculty.data.remote.service.FacultyApiImpl
import com.example.facultyofexactscience.feature.faculty.data.repository.EventsRepositoryImpl
import com.example.facultyofexactscience.feature.faculty.data.repository.QuotesRepositoryImpl
import com.example.facultyofexactscience.feature.faculty.domain.repository.EventsRepository
import com.example.facultyofexactscience.feature.faculty.domain.repository.QuotesRepository
import io.ktor.client.engine.okhttp.OkHttp

object AppModule {
    private val httpClient by lazy {
        // use OkHttp engine
        HttpClientFactory.create(OkHttp.create())
    }
    private val facultyApi: FacultyApi by lazy { FacultyApiImpl(httpClient) }

    val eventsRepository: EventsRepository by lazy { EventsRepositoryImpl(facultyApi) }
    val quotesRepository: QuotesRepository by lazy { QuotesRepositoryImpl(facultyApi) }
}

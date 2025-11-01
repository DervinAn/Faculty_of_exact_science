package com.example.facultyofexactscience.di

import android.util.Log
import com.example.facultyofexactscience.core.data.networking.HttpClientFactory
import com.example.facultyofexactscience.faculty.data.remote.api.FacultyApi
import com.example.facultyofexactscience.faculty.data.remote.service.FacultyApiImpl
import com.example.facultyofexactscience.faculty.data.repository.EventImagesRepositoryImpl
import com.example.facultyofexactscience.faculty.data.repository.EventsRepositoryImpl
import com.example.facultyofexactscience.faculty.data.repository.QuotesRepositoryImpl
import com.example.facultyofexactscience.faculty.domain.repository.EventImagesRepository
import com.example.facultyofexactscience.faculty.domain.repository.EventsRepository
import com.example.facultyofexactscience.faculty.domain.repository.QuotesRepository
import io.ktor.client.engine.okhttp.OkHttp

object AppModule {
    private const val TAG = "AppModule"

    // ---- Dependencies ----
    private val httpClient by lazy {
        Log.d(TAG, "HttpClient initialized")
        HttpClientFactory.create(OkHttp.create())
    }

    private val facultyApi: FacultyApi by lazy {
        Log.d(TAG, "FacultyApi initialized")
        FacultyApiImpl(httpClient)
    }

    val quotesRepository: QuotesRepository by lazy {
        Log.d(TAG, "QuotesRepository initialized")
        QuotesRepositoryImpl(facultyApi)
    }
    val eventsRepository: EventsRepository by lazy {
        EventsRepositoryImpl(facultyApi)
    }
    val eventImagesRepository: EventImagesRepository by lazy {
        EventImagesRepositoryImpl(facultyApi)
    }



    // 🔹 If you already implemented EventsRepositoryImpl, you can expose it here:
    // val eventsRepository: EventsRepository by lazy { EventsRepositoryImpl(facultyApi) }

    // ---- Initialization guard ----
    @Volatile private var initialized = false

    fun ensureInitialized() {
        if (initialized) return
        synchronized(this) {
            if (initialized) return
            // Force lazy init so they are ready at app start
            httpClient
            facultyApi
            quotesRepository
            eventsRepository; eventImagesRepository
            // eventsRepository   // uncomment if you have it
            initialized = true
            Log.d(TAG, "ensureInitialized() completed successfully")
        }
    }
}

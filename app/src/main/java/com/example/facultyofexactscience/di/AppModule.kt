package com.example.facultyofexactscience.di

import android.content.Context
import android.util.Log
import com.example.facultyofexactscience.core.data.networking.HttpClientFactory
import com.example.facultyofexactscience.core.data.remote.api.FacultyApi
import com.example.facultyofexactscience.core.data.remote.service.FacultyApiImpl
import com.example.facultyofexactscience.events.data.EventImagesRepositoryImpl
import com.example.facultyofexactscience.events.data.EventsRepositoryImpl
import com.example.facultyofexactscience.events.domain.EventImagesRepository
import com.example.facultyofexactscience.events.domain.EventsRepository
import com.example.facultyofexactscience.quotes.data.QuotesRepositoryImpl
import com.example.facultyofexactscience.quotes.domain.QuotesRepository

object AppModule {
    private const val TAG = "AppModule"

    // Optional: set this from Application to enable OkHttp disk cache
    private var appContext: Context? = null
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    // ---- Dependencies ----
    private val httpClient by lazy {
        Log.d(TAG, "HttpClient initialized")
        // ✅ FIX: HttpClientFactory expects Context? (not an engine)
        HttpClientFactory.create(appContext)
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

    // ---- Initialization guard ----
    @Volatile
    private var initialized = false

    fun ensureInitialized() {
        if (initialized) return
        synchronized(this) {
            if (initialized) return
            httpClient
            facultyApi
            quotesRepository
            eventsRepository
            eventImagesRepository
            initialized = true
            Log.d(TAG, "ensureInitialized() completed successfully")
        }
    }
}

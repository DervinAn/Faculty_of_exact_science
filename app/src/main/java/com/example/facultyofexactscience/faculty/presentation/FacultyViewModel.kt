package com.example.facultyofexactscience.faculty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.di.AppModule
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.example.facultyofexactscience.faculty.domain.Time
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FacultyViewModel : ViewModel() {

    private val eventsRepo = AppModule.eventsRepository
    private val imagesRepo = AppModule.eventImagesRepository
    private val _heroImages = MutableStateFlow<List<String>>(emptyList())
    val heroImages: StateFlow<List<String>> = _heroImages
    private val _heroIndex = MutableStateFlow(0)
    val heroIndex: StateFlow<Int> = _heroIndex


    private val quotesRepo = AppModule.quotesRepository
    // private val eventsRepo = AppModule.eventsRepository  // uncomment once events repo is ready

    private val _quotes = MutableStateFlow<List<Quotes>>(emptyList())
    val quotes: StateFlow<List<Quotes>> = _quotes

    // NEW: expose events flow (temporary placeholder until repo is wired)
    private val _events = MutableStateFlow<List<Event>>(
        listOf(
            Event("Welcome Day", "Meet faculty & labs", "2025-02-01", Time("09:00","11:00"), false),
            Event("Math Colloquium", "Graph theory and apps", "2025-02-04", Time("14:00","16:00"), false),
            Event("AI Workshop", "Intro to ML for science", "2025-02-08", Time("10:00","12:00"), false)
        )
    )
    val events: StateFlow<List<Event>> = _events

    init {
        refreshQuotes()
        refreshEvents()
        refreshHeroImages()
        autoRotateHero()
    }
    fun refreshEvents() = viewModelScope.launch {
        when (val res = eventsRepo.getAll()) {
            is Result.Success -> _events.value = res.data
            is Result.Error   -> _events.value = emptyList()
        }
    }

    fun refreshHeroImages() = viewModelScope.launch {
        when (val res = imagesRepo.getAll()) {
            is Result.Success -> _heroImages.value = res.data
            is Result.Error   -> _heroImages.value = emptyList()
        }
    }

    private fun autoRotateHero() = viewModelScope.launch {
        while (true) {
            kotlinx.coroutines.delay(10_000)
            val size = _heroImages.value.size
            if (size > 0) _heroIndex.value = (_heroIndex.value + 1) % size
        }
    }

    fun refreshQuotes() = viewModelScope.launch {
        when (val res = quotesRepo.getAll()) {
            is Result.Success -> _quotes.value = res.data
            is Result.Error   -> _quotes.value = emptyList()
        }
    }

}

package com.example.facultyofexactscience.faculty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.di.AppModule
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FacultyViewModel : ViewModel() {

    private val eventsRepo = AppModule.eventsRepository
    private val quotesRepo = AppModule.quotesRepository

    private val _quotes = MutableStateFlow<List<Quotes>>(emptyList())
    val quotes: StateFlow<List<Quotes>> = _quotes

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    init {
        refreshQuotes()
        refreshEvents()
    }

    fun refreshQuotes() = viewModelScope.launch {
        when (val res = quotesRepo.getAll()) {
            is Result.Success -> _quotes.value = res.data
            is Result.Error   -> _quotes.value = emptyList() // TODO: expose error state if needed
        }
    }

    fun refreshEvents() = viewModelScope.launch {
        when (val res = eventsRepo.getAll()) {
            is Result.Success -> _events.value = res.data
            is Result.Error   -> _events.value = emptyList()
        }
    }
}

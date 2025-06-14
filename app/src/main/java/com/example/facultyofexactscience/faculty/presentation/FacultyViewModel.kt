package com.example.facultyofexactscience.faculty.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultyofexactscience.faculty.data.FirebaseRepository
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FacultyViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    private val _quotes = MutableStateFlow<List<Quotes>>(emptyList())
    val quotes: StateFlow<List<Quotes>> = _quotes

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    init {
        loadQuotes()
        loadEvents()
    }

    @SuppressLint("NewApi")
    private fun loadQuotes() {
        viewModelScope.launch {
            _quotes.value = repository.fetchQuotes()
        }
    }

    @SuppressLint("NewApi")
    private fun loadEvents() {
        viewModelScope.launch {
            _events.value = repository.fetchEvents()
        }
    }
}

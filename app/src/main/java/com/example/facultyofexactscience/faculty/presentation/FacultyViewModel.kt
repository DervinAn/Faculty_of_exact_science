package com.example.facultyofexactscience.faculty.presentation

import androidx.lifecycle.ViewModel
import com.example.facultyofexactscience.faculty.data.FirebaseRepository
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FacultyViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    private val _quotes = MutableStateFlow<List<Quotes>>(emptyList())
    val quotes: StateFlow<List<Quotes>> = _quotes

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private var quotesListener: ListenerRegistration? = null
    private var eventsListener: ListenerRegistration? = null

    init {
        quotesListener = repository.fetchQuotes { updatedQuotes ->
            _quotes.value = updatedQuotes
        }

        eventsListener = repository.fetchEvents { updatedEvents ->
            _events.value = updatedEvents
        }
    }

    override fun onCleared() {
        quotesListener?.remove()
        eventsListener?.remove()
        super.onCleared()
    }
}

package com.example.facultyofexactscience.faculty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultyofexactscience.faculty.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(): ViewModel() {


    private val _events =  MutableStateFlow<Event?>(null)
    val events : StateFlow<Event?> get() = _events

    private val _slides =  MutableStateFlow<Event?>(null)
    val slides : StateFlow<Event?> get() = _slides


    fun fetchEvents() {
        viewModelScope.launch {
            try {
                /*Making the network request on a background thread*/
                /*val response: Response<Event?> = withContext(Dispatchers.IO) {
                    /*No type arguments expected for class Response*/
                }

                if (response.isSuccessful) {
                    _events.value = response.body()
                } else {
                    _events.value = "Error: ${response.code()} - ${response.message()}"
                }*/
            } catch (e: Exception) {
                /* Handling any exceptions such as network error*/

            }
        }
    }

    fun fetchSlides() {
        viewModelScope.launch {
            try {
                /*Making the network request on a background thread*/
                /*val response: Response<Event?> = withContext(Dispatchers.IO) {
                    /*No type arguments expected for class Response*/
                }

                if (response.isSuccessful) {
                    _slides.value = response.body()
                } else {
                    _slides.value = "Error: ${response.code()} - ${response.message()}"
                }*/
            } catch (e: Exception) {
                /* Handling any exceptions such as network error*/

            }
        }
    }

}
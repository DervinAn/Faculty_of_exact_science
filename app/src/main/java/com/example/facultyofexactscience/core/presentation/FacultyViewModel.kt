package com.example.facultyofexactscience.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultyofexactscience.core.domain.util.Result
import com.example.facultyofexactscience.di.AppModule
import com.example.facultyofexactscience.events.domain.Event
import com.example.facultyofexactscience.quotes.domain.Quotes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class TvUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val events: List<Event> = emptyList(),
    val heroImages: List<String> = emptyList(),
    val heroIndex: Int = 0,
    val quotes: List<Quotes> = emptyList(),
    val quoteIndex: Int = 0,
)

class FacultyViewModel : ViewModel() {

    private val eventsRepo = AppModule.eventsRepository
    private val imagesRepo = AppModule.eventImagesRepository
    private val quotesRepo = AppModule.quotesRepository

    private val _state = MutableStateFlow(TvUiState())
    val state: StateFlow<TvUiState> = _state

    private val heroRotateMs = 10_000L
    private val quoteRotateMs = 12_000L
    private val pollEveryMs = 5 * 60_000L

    init {
        refreshAll()
        startHeroRotation()
        startQuoteRotation()
        startPolling()
    }

    fun refreshAll() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, error = null) }

        val eventsRes = eventsRepo.getAll()
        val imagesRes = imagesRepo.getAll()
        val quotesRes = quotesRepo.getAll()

        _state.update { s ->
            s.copy(
                isLoading = false,
                events = (eventsRes as? Result.Success)?.data ?: s.events,
                heroImages = (imagesRes as? Result.Success)?.data ?: s.heroImages,
                quotes = (quotesRes as? Result.Success)?.data ?: s.quotes,
                error =
                    when {
                        eventsRes is Result.Error -> "Failed to load events"
                        imagesRes is Result.Error -> "Failed to load images"
                        quotesRes is Result.Error -> "Failed to load quotes"
                        else -> null
                    }
            )
        }
    }

    private fun startHeroRotation() = viewModelScope.launch {
        while (isActive) {
            delay(heroRotateMs)
            _state.update { s ->
                val size = s.heroImages.size
                if (size == 0) s else s.copy(heroIndex = (s.heroIndex + 1) % size)
            }
        }
    }

    private fun startQuoteRotation() = viewModelScope.launch {
        while (isActive) {
            delay(quoteRotateMs)
            _state.update { s ->
                val size = s.quotes.size
                if (size == 0) s else s.copy(quoteIndex = (s.quoteIndex + 1) % size)
            }
        }
    }

    private fun startPolling() = viewModelScope.launch {
        while (isActive) {
            delay(pollEveryMs)
            refreshAll()
        }
    }
}

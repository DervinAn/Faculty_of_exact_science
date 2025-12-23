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

data class HeroSlide(
    val eventId: Long?,
    val eventIndex: Int,
    val imageUrl: String?, // null -> UI shows placeholder
)

data class TvUiState(
    val isLoading: Boolean = true,
    val error: String? = null,

    val events: List<Event> = emptyList(),

    // derived from events.images
    val heroSlides: List<HeroSlide> = emptyList(),
    val heroSlideIndex: Int = 0,

    // selection synced with heroSlides
    val selectedEventId: Long? = null,
    val selectedEventIndex: Int = 0,

    val quotes: List<Quotes> = emptyList(),
    val quoteIndex: Int = 0
)

class FacultyViewModel : ViewModel() {

    companion object {
        const val HERO_ROTATE_MS = 8_000L
        const val QUOTE_ROTATE_MS = 12_000L
        const val POLL_EVERY_MS = 5 * 60_000L
    }

    private val eventsRepo = AppModule.eventsRepository
    private val quotesRepo = AppModule.quotesRepository

    private val _state = MutableStateFlow(TvUiState())
    val state: StateFlow<TvUiState> = _state

    init {
        refreshAll()
        startHeroRotation()
        startQuoteRotation()
        startPolling()
    }

    fun onEventFocused(index: Int) {
        _state.update { s ->
            if (s.events.isEmpty()) return@update s

            val safeIndex = index.coerceIn(0, s.events.lastIndex)
            val id = s.events.getOrNull(safeIndex)?.id
            val slideIndex = s.heroSlides.indexOfFirst { it.eventIndex == safeIndex }.let {
                if (it == -1) 0 else it
            }

            s.copy(
                selectedEventIndex = safeIndex,
                selectedEventId = id,
                heroSlideIndex = slideIndex
            )
        }
    }

    fun refreshAll() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, error = null) }

        val eventsRes = eventsRepo.getAll() // repo should already filter upcoming + sort
        val quotesRes = quotesRepo.getAll(forceRefresh = true)

        _state.update { old ->
            val newEvents = (eventsRes as? Result.Success)?.data ?: old.events
            val newSlides = buildHeroSlides(newEvents)

            // keep selection by ID across refresh
            val targetIndexById =
                old.selectedEventId?.let { selId -> newEvents.indexOfFirst { it.id == selId } } ?: -1

            val selectedIndex =
                when {
                    newEvents.isEmpty() -> 0
                    targetIndexById >= 0 -> targetIndexById
                    else -> old.selectedEventIndex.coerceIn(0, newEvents.lastIndex)
                }

            val selectedId = newEvents.getOrNull(selectedIndex)?.id

            val newHeroSlideIndex =
                newSlides.indexOfFirst { it.eventIndex == selectedIndex }.let { if (it == -1) 0 else it }

            old.copy(
                isLoading = false,
                events = newEvents,
                heroSlides = newSlides,
                selectedEventIndex = selectedIndex,
                selectedEventId = selectedId,
                heroSlideIndex = newHeroSlideIndex,
                quotes = (quotesRes as? Result.Success)?.data ?: old.quotes,
                error =
                    when {
                        eventsRes is Result.Error -> "Failed to load events"
                        quotesRes is Result.Error -> "Failed to load quotes"
                        else -> null
                    }
            )
        }
    }

    private fun startHeroRotation() = viewModelScope.launch {
        while (isActive) {
            delay(HERO_ROTATE_MS)
            _state.update { s ->
                val slides = s.heroSlides
                if (slides.size < 2) return@update s

                val nextSlideIndex = (s.heroSlideIndex + 1) % slides.size
                val nextEventIndex = slides[nextSlideIndex].eventIndex
                val nextEventId = s.events.getOrNull(nextEventIndex)?.id

                s.copy(
                    heroSlideIndex = nextSlideIndex,
                    selectedEventIndex = nextEventIndex,
                    selectedEventId = nextEventId
                )
            }
        }
    }

    private fun startQuoteRotation() = viewModelScope.launch {
        while (isActive) {
            delay(QUOTE_ROTATE_MS)
            _state.update { s ->
                val size = s.quotes.size
                if (size < 2) s else s.copy(quoteIndex = (s.quoteIndex + 1) % size)
            }
        }
    }

    private fun startPolling() = viewModelScope.launch {
        while (isActive) {
            delay(POLL_EVERY_MS)
            refreshAll()
        }
    }

    private fun buildHeroSlides(events: List<Event>): List<HeroSlide> {
        if (events.isEmpty()) return emptyList()

        val slides = ArrayList<HeroSlide>(events.size * 2)
        events.forEachIndexed { idx, ev ->
            val imgs = ev.images.filter { it.isNotBlank() }
            if (imgs.isEmpty()) {
                slides += HeroSlide(ev.id, idx, null)
            } else {
                imgs.forEach { url ->
                    slides += HeroSlide(ev.id, idx, url)
                }
            }
        }
        return slides
    }
}

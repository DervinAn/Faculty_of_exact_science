//package com.example.facultyofexactscience.viewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.tracing.perfetto.handshake.protocol.Response
//import com.example.facultyofexactscience.api.ApiService
//import com.example.facultyofexactscience.presentation.ui.event.Event
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class EventViewModel(private val apiService: ApiService): ViewModel() {
//
//    private val _events = MutableLiveData<Event>()
//    val events : LiveData<Event> get() = _events
//
//    private val _error = MutableLiveData<String>()
//    val error: LiveData<String> get() = _error
//
//    fun fetchDataFromApi() {
//        viewModelScope.launch {
//            try {
//                /*Making the network request on a background thread*/
//                val response: Response<Event> = withContext(Dispatchers.IO) {
//                    apiService.fetchData()
//                }
//
//                if (response.isSuccessful) {
//                    _events.value = response.body()
//                } else {
////                    _events.value = "Error: ${response.code()} - ${response.message()}"
//                }
//            } catch (e: Exception) {
//                /* Handling any exceptions such as network error*/
//                _error.value = "Exception: ${e.message}"
//            }
//        }
//    }
//
//}
package com.example.facultyofexactscience.faculty.data

import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {

    private val firestore = Firebase.firestore


    suspend fun fetchEvents(): List<Event> = try {
        firestore.collection("events")
            .get()
            .await()
            .map { it.toObject(Event::class.java) }
    } catch (_: Exception) {
        emptyList()
    }


    suspend fun fetchQuotes(): List<Quotes> = try {
        firestore.collection("quotes")
            .get()
            .await()
            .map { it.toObject(Quotes::class.java) }
    } catch (_: Exception) {
        emptyList()
    }
}

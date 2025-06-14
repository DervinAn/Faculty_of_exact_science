package com.example.facultyofexactscience.faculty.data

import android.util.Log
import com.example.facultyofexactscience.faculty.domain.Event
import com.example.facultyofexactscience.faculty.domain.Quotes
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepository {

    private val firestore = Firebase.firestore
    private val TAG = "FirebaseRepository"

    fun fetchQuotes(onUpdate: (List<Quotes>) -> Unit): ListenerRegistration {
        return firestore.collection("quotes")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "Error fetching quotes: ${error.message}", error)
                    onUpdate(emptyList())
                    return@addSnapshotListener
                }

                if (snapshot == null) {
                    Log.w(TAG, "Snapshot for quotes is null.")
                    onUpdate(emptyList())
                    return@addSnapshotListener
                }

                val quotes = snapshot.documents.mapNotNull { doc ->
                    try {
                        val quote = doc.toObject(Quotes::class.java)
                        quote?.copy(id = doc.id)
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to convert quote: ${doc.id}", e)
                        null
                    }
                }


                Log.d(TAG, "Fetched ${quotes.size} quotes from Firebase")
                onUpdate(quotes)
            }
    }

    fun fetchEvents(onUpdate: (List<Event>) -> Unit): ListenerRegistration {
        return firestore.collection("events")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "Error fetching events: ${error.message}", error)
                    onUpdate(emptyList())
                    return@addSnapshotListener
                }

                if (snapshot == null) {
                    Log.w(TAG, "Snapshot for events is null.")
                    onUpdate(emptyList())
                    return@addSnapshotListener
                }

                val events = snapshot.documents.mapNotNull {
                    try {
                        it.toObject(Event::class.java)
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to convert event: ${it.id}", e)
                        null
                    }
                }

                Log.d(TAG, "Fetched ${events.size} events from Firebase")
                onUpdate(events)
            }
    }
}

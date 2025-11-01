package com.example.facultyofexactscience

import android.app.Application
import android.util.Log
import com.example.facultyofexactscience.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FacultyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.ensureInitialized()

        // 🔎 TEMP: kick off one call so we see logs
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("SmokeTest", "Fetching /events...")
              //  val result = AppModule.eventsRepository.getAll()
                //Log.d("SmokeTest", "Result: $result")
            } catch (t: Throwable) {
                Log.e("SmokeTest", "Error calling /events", t)
            }
        }
    }
}

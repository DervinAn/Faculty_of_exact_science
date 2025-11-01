// app/src/main/java/com/example/facultyofexactscience/faculty/presentation/dashboard/DateUtils.kt
package com.example.facultyofexactscience.faculty.presentation.components.dashboard

import java.text.SimpleDateFormat
import java.util.*

fun toDayMonth(dateStr: String): Pair<String, String> = try {
    val inFmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outDay = SimpleDateFormat("dd", Locale.getDefault())
    val outMon = SimpleDateFormat("MMM", Locale.getDefault())
    val d = inFmt.parse(dateStr)!!
    outDay.format(d).padStart(2, '0') to outMon.format(d).uppercase(Locale.getDefault())
} catch (_: Exception) { "--" to "---" }

package com.example.facultyofexactscience.core.data.networking

fun String.toStorageUrl(): String {
    if (startsWith("http://") || startsWith("https://")) return this
    val clean = trimStart('/')
    return "${ApiConfig.STORAGE_BASE_URL}/$clean"
}

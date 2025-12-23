package com.example.facultyofexactscience.core.data.networking

fun constructUrl(endpoint: String): String {
    val base = ApiConfig.BASE_API_URL.trimEnd('/')
    val path = endpoint.trimStart('/')
    return "$base/$path"
}

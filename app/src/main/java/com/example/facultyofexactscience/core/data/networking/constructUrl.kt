package com.example.facultyofexactscience.core.data.networking

import com.example.facultyofexactscience.core.AppConfig

fun constructUrl(url: String): String {
    val base = AppConfig.BASE_URL
    return when {
        url.startsWith(base) -> url
        url.startsWith("/")  -> base + url.drop(1)
        else                 -> base + url
    }
}

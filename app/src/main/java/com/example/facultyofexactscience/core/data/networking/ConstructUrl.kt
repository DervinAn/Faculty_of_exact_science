package com.example.facultyofexactscience.core.data.networking

/**
fun constructUrl(url:String):String{
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}*/
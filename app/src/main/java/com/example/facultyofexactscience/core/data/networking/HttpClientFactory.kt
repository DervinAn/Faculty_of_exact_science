//package com.example.facultyofexactscience.core.data.networking
//
//import android.net.http.HttpResponseCache.install
//import com.google.android.datatransport.runtime.logging.Logging
//import io.ktor.client.*
//import io.ktor.client.engine.*
//import io.ktor.http.*
//import kotlinx.serialization.json.Json
//
//
//object HttpClientFactory {
//    fun create(engine: HttpClientEngine): HttpClient {
//        return HttpClient(engine) {
//            install(Logging) {
//                level = LogLevel.ALL
//                logger = Logger.ANDROID
//            }
//            install(ContentNegotiation) {
//                json(
//                    json = Json {
//                        ignoreUnknownKeys = true
//                    }
//                )
//            }
//            defaultRequest {
//                contentType(ContentType.Application.Json)
//            }
//        }
//    }
//}
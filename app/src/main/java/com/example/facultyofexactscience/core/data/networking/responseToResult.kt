//package com.example.facultyofexactscience.core.data.networking
//
//import com.example.facultyofexactscience.core.domain.util.NetworkError
//import com.example.facultyofexactscience.core.domain.util.Result
//
//suspend inline fun <reified T> responseToResult(
//    response: HttpResponse,
//): Result<T, NetworkError> {
//    return when (response.status.value) {
//        in 200..299 -> {
//            try {
//                Result.Success(response.body<T>())
//            } catch (e: NoTransformationFoundException) {
//                Result.Error(NetworkError.SERIALIZATION)
//            }
//        }
//        408 -> Result.Error(NetworkError.REQUEST_TIME_OUT)
//        429 -> Result.Error(NetworkError.TOO_MANY_REQUEST)
//        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
//        else -> Result.Error(NetworkError.UNKNOWN)
//    }
//}
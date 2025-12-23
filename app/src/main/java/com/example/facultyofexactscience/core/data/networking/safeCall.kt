package com.example.facultyofexactscience.core.data.networking

import com.example.facultyofexactscience.core.domain.util.NetworkError
import com.example.facultyofexactscience.core.domain.util.Result
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (_: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (_: UnknownHostException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (_: ConnectException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (_: SocketTimeoutException) {
        return Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (_: HttpRequestTimeoutException) {
        return Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (_: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (_: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}

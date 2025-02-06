package com.task.bosta.utils.network

import com.task.bosta.data.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

fun <T> makeRequest(callApi: suspend () -> BaseResponse<T>): Flow<State<T>> = flow {
    try {
        val response = callApi()
        emit(State.Success(response))
    }
    // Handle when the API request times out due to a slow network or server.
    catch (e: SocketTimeoutException) {
        emit(State.Error(message = "Request timed out: ${e.message}"))
    }
    // Handle when the host (e.g., API server) cannot be resolved (e.g., invalid URL or no internet).
    catch (e: UnknownHostException) {
        emit(State.Error(message = "Unknown host: ${e.message}"))
    }
    // Handle when the API request fails with an HTTP error code (e.g., 404, 500).
    catch (e: HttpException) {
        emit(State.Error(message = "HTTP error: ${e.code()}, ${e.message}"))
    }
    // For general network-related issues (e.g., no internet, connection reset).
    catch (e: IOException) {
        emit(State.Error(message = "Network error: ${e.message}"))
    }
    // Handle when there’s an issue parsing the API response (e.g., malformed JSON).
    catch (e: JSONException) {
        emit(State.Error(message = "JSON parsing error: ${e.message}"))
    }
    // Handle when a coroutine is canceled, and you want to handle the cancellation gracefully.
    catch (e: CancellationException) {
        emit(State.Error(message = "Request canceled: ${e.message}"))
    }
    // Catch any unexpected exceptions that weren’t caught by the more specific handlers.
    catch (e: Exception) {
        emit(State.Error(message = "Unexpected error: ${e.message}"))
    }
}.onStart {
    emit(State.Loading)
}.flowOn(Dispatchers.IO)

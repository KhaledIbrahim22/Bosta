package com.task.bosta.utils.network

import com.task.bosta.data.model.BaseResponse

sealed class State<out T> {
    data object Loading : State<Nothing>()
    class Success<T>(val response: BaseResponse<T>) : State<T>()
    class Error(val message: String?) : State<Nothing>()
}

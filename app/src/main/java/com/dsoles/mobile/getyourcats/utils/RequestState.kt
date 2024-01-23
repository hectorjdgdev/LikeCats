package com.dsoles.mobile.getyourcats.utils

sealed class RequestState<T>(val data:T? = null, val message: String? = null){
    class Success<T>(data: T): RequestState<T>(data)
    class Error<T>(message: String): RequestState<T>(null, message)
}

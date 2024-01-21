package com.dsoles.mobile.getyourcats.utils

//sealed class RequestState<T>{
//    var error: String? = null
//    data class Success<T>(val data: T) : RequestState<T>()
//    data class Error<T>(val errorValue: String) : RequestState<T>()
//    data class Loading<T>(val data: T?= null) : RequestState<T>()
//}

sealed class RequestState{
    object Loading : RequestState()
    object Success : RequestState()
    data class Error(val exception: String) : RequestState()
}

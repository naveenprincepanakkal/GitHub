package com.naveenprince.github.utils

/**
 * Class used to handle api service response status and load UI status
 *
 * Created by Naveen.
 */
sealed class ResponseStatus<out T> {
    data class Success<T>(val data: T?) : ResponseStatus<T>()
    data class Error(val statusCode: Int?, val message: String) : ResponseStatus<Nothing>()
    //data class Loading(val message: String? = "") : ResponseStatus<Nothing>()
    //data object Empty : ResponseStatus<Nothing>()
    //data object NetworkError : ResponseStatus<Nothing>()
}

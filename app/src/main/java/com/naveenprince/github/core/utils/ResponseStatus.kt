package com.naveenprince.github.core.utils

/**
 * Class used to handle api service response status and load UI status
 *
 * Created by Naveen.
 */
sealed class ResponseStatus<out T> {
    data class Success<T>(val data: T?) : ResponseStatus<T>()
    data class Error(val statusCode: Int? = null, val message: String) : ResponseStatus<Nothing>()
}

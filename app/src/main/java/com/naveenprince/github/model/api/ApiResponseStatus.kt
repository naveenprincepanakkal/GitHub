package com.naveenprince.github.model.api

import com.naveenprince.github.utils.ResponseStatus
import retrofit2.Response

/**
 * Class used ot check API response
 *
 * Created by Naveen.
 */

sealed class ApiResponse {
    companion object {
        fun <T> create(response: Response<T>): ResponseStatus<T> {
            return try {
                if (response.isSuccessful) {
                    val body = response.body()
                    ResponseStatus.Success(body)
                } else {
                    val msg = response.errorBody()?.string()
                    val errorMessage = if (msg.isNullOrEmpty()) {
                        response.message()
                    } else {
                        msg
                    }
                    ResponseStatus.Error(response.code(), errorMessage)
                }
            } catch (e: Exception) {
                ResponseStatus.Error(null, "An error occurred: ${e.message}")
            }
        }
    }
}


package com.naveenprince.github.model.api

import com.naveenprince.github.model.data.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Class for calling all user services
 *
 * Created by Naveen.
 */
interface UsersService {

    @GET
    suspend fun userDetails(@Url url: String): Response<UserDetailsResponse>

}
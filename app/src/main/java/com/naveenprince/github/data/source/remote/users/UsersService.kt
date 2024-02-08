package com.naveenprince.github.data.source.remote.users

import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Class for calling all user services
 *
 * Created by Naveen.
 */
interface UsersService {

    @GET
    suspend fun userDetails(@Url url: String): UserDetailsResponse

}
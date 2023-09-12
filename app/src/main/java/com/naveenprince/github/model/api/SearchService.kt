package com.naveenprince.github.model.api

import com.naveenprince.github.model.data.SearchUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class for calling all search services
 *
 * Created by Naveen.
 */
interface SearchService {

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<SearchUsersResponse>

}
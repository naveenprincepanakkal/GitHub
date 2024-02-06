package com.naveenprince.github.data.source.remote.search

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
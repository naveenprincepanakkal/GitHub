package com.naveenprince.github.data.search.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class for calling all search services
 *
 * Created by Naveen.
 */
interface SearchService {

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): SearchUsersResponse

}
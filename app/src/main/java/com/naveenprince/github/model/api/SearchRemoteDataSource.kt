package com.naveenprince.github.model.api

import com.naveenprince.github.model.data.SearchUsersResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface SearchRemoteDataSource {

    fun searchUsers(query: String): Flow<ResponseStatus<SearchUsersResponse>>

}

package com.naveenprince.github.data.source.remote.search

import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface SearchRemoteDataSource {

    fun searchUsers(query: String): Flow<ResponseStatus<SearchUsersResponse>>

}

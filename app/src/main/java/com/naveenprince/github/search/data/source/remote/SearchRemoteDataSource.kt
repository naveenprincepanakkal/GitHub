package com.naveenprince.github.search.data.source.remote

import com.naveenprince.github.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface SearchRemoteDataSource {

    fun searchUsers(query: String): Flow<ResponseStatus<SearchUsersResponse>>

}

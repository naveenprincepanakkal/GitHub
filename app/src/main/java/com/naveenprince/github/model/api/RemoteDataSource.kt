package com.naveenprince.github.model.api

import com.naveenprince.github.model.data.SearchUsersResponse
import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Data source class for services
 *
 * Created by Naveen.
 */
class RemoteDataSource @Inject constructor(
    private val searchService: SearchService,
    private val usersService: UsersService,
) {

    fun searchUsers(query: String): Flow<ResponseStatus<SearchUsersResponse>> = flow {
        emit(ApiResponse.create(searchService.searchUsers(query)))
    }

    fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>> = flow {
        emit(ApiResponse.create(usersService.userDetails(url)))
    }

}
package com.naveenprince.github.data.source.remote.users

import com.naveenprince.github.utils.ApiResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Naveen.
 */
class UsersRemoteDataSourceImpl @Inject constructor(
    private val usersService: UsersService
) : UsersRemoteDataSource {

    override fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>> = flow {
        emit(ApiResponse.create(usersService.userDetails(url)))
    }

}
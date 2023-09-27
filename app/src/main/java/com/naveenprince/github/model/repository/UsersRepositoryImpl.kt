package com.naveenprince.github.model.repository

import com.naveenprince.github.model.api.UsersRemoteDataSource
import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository class to handle all user services
 *
 * Created by Naveen.
 */
class UsersRepositoryImpl @Inject constructor(private val remoteDataSource: UsersRemoteDataSource) :
    UsersRepository {

    override fun fetchUserDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>> =
        remoteDataSource.userDetails(url)

}
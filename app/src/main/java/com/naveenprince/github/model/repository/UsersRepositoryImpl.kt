package com.naveenprince.github.model.repository

import com.naveenprince.github.model.api.UsersRemoteDataSource
import com.naveenprince.github.model.data.UserDetails
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class to handle all user services
 *
 * Created by Naveen.
 */
class UsersRepositoryImpl @Inject constructor(private val remoteDataSource: UsersRemoteDataSource) :
    UsersRepository {

    override fun fetchUserDetails(url: String): Flow<ResponseStatus<UserDetails>> = flow {
        remoteDataSource.userDetails(url).collect {
            when (it) {
                is ResponseStatus.Success -> {
                    val userDetails = it.data?.let { userDetails -> UserDetails(userDetails) }
                    emit(ResponseStatus.Success(userDetails))
                }

                is ResponseStatus.Error -> {
                    emit(ResponseStatus.Error(it.statusCode, it.message))
                }

                else -> {
                    emit(ResponseStatus.Loading())
                }
            }

        }

    }

}
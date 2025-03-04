package com.naveenprince.github.data.users.repository

import com.naveenprince.github.data.users.mapper.UserDetailsMapper
import com.naveenprince.github.data.users.source.remote.UsersRemoteDataSource
import com.naveenprince.github.domain.users.model.UserDetails
import com.naveenprince.github.domain.users.repository.UsersRepository
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
                    val userDetails = it.data?.let { userDetails -> UserDetailsMapper.fromResponse(userDetails) }
                    emit(ResponseStatus.Success(userDetails))
                }

                is ResponseStatus.Error -> {
                    emit(ResponseStatus.Error(it.statusCode, it.message))
                }

            }
        }
    }
}
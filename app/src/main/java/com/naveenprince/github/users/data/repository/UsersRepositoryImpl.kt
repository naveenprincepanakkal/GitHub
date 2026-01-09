package com.naveenprince.github.users.data.repository

import com.naveenprince.github.core.utils.ResponseStatus
import com.naveenprince.github.users.data.mapper.UserDetailsMapper
import com.naveenprince.github.users.data.source.remote.UsersRemoteDataSource
import com.naveenprince.github.users.domain.model.UserDetails
import com.naveenprince.github.users.domain.repository.UsersRepository
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
                    val userDetails =
                        it.data?.let { userDetails -> UserDetailsMapper.fromResponse(userDetails) }
                    emit(ResponseStatus.Success(userDetails))
                }

                is ResponseStatus.Error -> {
                    emit(ResponseStatus.Error(it.statusCode, it.message))
                }

            }
        }
    }
}
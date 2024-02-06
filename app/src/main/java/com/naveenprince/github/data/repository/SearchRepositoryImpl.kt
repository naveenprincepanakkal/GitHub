package com.naveenprince.github.data.repository

import com.naveenprince.github.data.source.remote.search.SearchRemoteDataSource
import com.naveenprince.github.domain.model.User
import com.naveenprince.github.domain.repository.SearchRepository
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class to handle all Search services
 *
 * Created by Naveen.
 */
class SearchRepositoryImpl @Inject constructor(private val remoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override fun searchUsers(query: String): Flow<ResponseStatus<List<User>>> = flow {
        remoteDataSource.searchUsers(query).collect {
            when (it) {
                is ResponseStatus.Success -> {
                    val user: List<User> =
                        it.data?.userList?.map { users -> User(users) } ?: listOf()
                    emit(ResponseStatus.Success(user))
                }

                is ResponseStatus.Error -> {
                    emit(ResponseStatus.Error(it.statusCode, it.message))
                }

            }
        }
    }
}
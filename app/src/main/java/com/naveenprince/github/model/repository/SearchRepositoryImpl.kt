package com.naveenprince.github.model.repository

import com.naveenprince.github.model.api.RemoteDataSource
import com.naveenprince.github.model.data.SearchUsersResponse
import com.naveenprince.github.model.data.User
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class to handle all Search services
 *
 * Created by Naveen.
 */
class SearchRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    SearchRepository {

    override suspend fun searchUsers(query: String): Flow<ResponseStatus<List<User>>> = flow {
        var searchResponse: SearchUsersResponse?
        remoteDataSource.searchUsers(query).collect {
            when (it) {
                is ResponseStatus.Success -> {
                    searchResponse = it.data
                    emit(ResponseStatus.Success(searchResponse?.userList))
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
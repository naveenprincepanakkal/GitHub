package com.naveenprince.github.data.repository

import com.google.gson.Gson
import com.naveenprince.github.data.source.remote.search.SearchRemoteDataSource
import com.naveenprince.github.data.source.remote.search.SearchUsersResponse
import com.naveenprince.github.domain.model.User
import com.naveenprince.github.domain.repository.SearchRepository
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Naveen.
 */
@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: SearchRemoteDataSource

    private lateinit var repository: SearchRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = SearchRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testSearchUsers_Success() = runTest {

        val query = "naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_search.json")?.readText()
        val searchUsersResponse: SearchUsersResponse =
            Gson().fromJson(jsonString, SearchUsersResponse::class.java)

        Mockito.`when`(remoteDataSource.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Success(searchUsersResponse)))

        val result: Flow<ResponseStatus<List<User>>> = repository.searchUsers(query)
        result.collect { apiResponseStatus ->
            when (apiResponseStatus) {
                is ResponseStatus.Success -> assertEquals(
                    searchUsersResponse.userList.map { User(it) },
                    apiResponseStatus.data
                )

                is ResponseStatus.Error -> assert(false)
            }
        }
    }

    @Test
    fun testSearchUsers_Error() = runTest {

        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error message"

        Mockito.`when`(remoteDataSource.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Error(errorCode, errorMessage)))

        val result: Flow<ResponseStatus<List<User>>> = repository.searchUsers(query)
        result.collect { apiResponseStatus ->
            when (apiResponseStatus) {
                is ResponseStatus.Success -> assert(false)
                is ResponseStatus.Error -> {
                    assertEquals(errorCode, apiResponseStatus.statusCode)
                    assertEquals(errorMessage, apiResponseStatus.message)
                }
            }
        }
    }
}

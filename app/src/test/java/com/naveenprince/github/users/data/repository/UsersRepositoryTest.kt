package com.naveenprince.github.users.data.repository

import com.naveenprince.github.users.data.mapper.UserDetailsMapper
import com.naveenprince.github.users.data.source.remote.UserDetailsResponse
import com.naveenprince.github.users.data.source.remote.UsersRemoteDataSource
import com.naveenprince.github.users.domain.model.UserDetails
import com.naveenprince.github.users.domain.repository.UsersRepository
import com.naveenprince.github.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
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
class UsersRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: UsersRemoteDataSource

    private lateinit var repository: UsersRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = UsersRepositoryImpl(remoteDataSource)
    }

    @Test
    fun fetchUserDetails_Success() = runTest {

        val queryUrl = "https://api.github.com/users/naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_details.json")?.readText()
        val userDetailsResponse: UserDetailsResponse =
            Json.decodeFromString<UserDetailsResponse>(jsonString.toString())

        Mockito.`when`(remoteDataSource.userDetails(queryUrl))
            .thenReturn(flowOf(ResponseStatus.Success(userDetailsResponse)))

        val result: Flow<ResponseStatus<UserDetails>> =
            repository.fetchUserDetails(queryUrl)
        result.collect { apiResponseStatus ->
            when (apiResponseStatus) {
                is ResponseStatus.Success -> assertEquals(
                    UserDetailsMapper.fromResponse(userDetailsResponse),
                    apiResponseStatus.data
                )

                is ResponseStatus.Error -> assert(false)
            }
        }
        Mockito.verify(remoteDataSource).userDetails(queryUrl)
    }

    @Test
    fun fetchUserDetails_Error() = runTest {

        val queryUrl = "invalid url"
        val errorCode = 403
        val errorMessage = "Error message"

        Mockito.`when`(remoteDataSource.userDetails(queryUrl))
            .thenReturn(flowOf(ResponseStatus.Error(errorCode, errorMessage)))
        val result: Flow<ResponseStatus<UserDetails>> =
            repository.fetchUserDetails(queryUrl)
        result.collect { apiResponseStatus ->
            when (apiResponseStatus) {
                is ResponseStatus.Success -> assert(false)
                is ResponseStatus.Error -> {
                    assertEquals(errorCode, apiResponseStatus.statusCode)
                    assertEquals(errorMessage, apiResponseStatus.message)
                }
            }
        }
        Mockito.verify(remoteDataSource).userDetails(queryUrl)
    }
}

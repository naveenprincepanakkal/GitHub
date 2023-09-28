package com.naveenprince.github.viewmodel

import com.google.gson.Gson
import com.naveenprince.github.model.data.UserDetails
import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.model.repository.UsersRepository
import com.naveenprince.github.utilities.MainDispatcherRule
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
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
class UserDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var usersRepository: UsersRepository
    private lateinit var userViewModel: UserDetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userViewModel = UserDetailsViewModel(usersRepository)
    }

    @Test
    @Throws(Exception::class)
    fun fetchUserDetails_LoadingStatus() = runTest {
        // Given
        val query = "loading"

        // When
        Mockito.`when`(usersRepository.fetchUserDetails(query))
            .thenReturn(flowOf(ResponseStatus.Loading()))
        userViewModel.fetchUserDetails(query)

        // Then
        val expectedResult = ResponseStatus.Loading()
        val actualResult = userViewModel.userDetailsState.value
        assertEquals(expectedResult, actualResult)
    }

    @Test
    @Throws(Exception::class)
    fun fetchUserDetails_SuccessStatus() = runTest {
        // Given
        val queryUrl = "https://api.github.com/users/naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_details.json")?.readText()
        val userDetailsResponse: UserDetailsResponse =
            Gson().fromJson(jsonString, UserDetailsResponse::class.java)


        // When
        Mockito.`when`(usersRepository.fetchUserDetails(queryUrl))
            .thenReturn(flowOf(ResponseStatus.Success(UserDetails(userDetailsResponse))))
        userViewModel.fetchUserDetails(queryUrl)

        // Then
        val expectedResult = ResponseStatus.Success(UserDetails(userDetailsResponse))
        val actualResult = userViewModel.userDetailsState.value
        assertEquals(expectedResult, actualResult)
    }

    @Test
    @Throws(Exception::class)
    fun fetchUserDetails_Error() = runTest {
        // Given
        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error fetching data"
        val errorResponse = ResponseStatus.Error(errorCode, errorMessage)

        // When
        Mockito.`when`(usersRepository.fetchUserDetails(query)).thenReturn(flowOf(errorResponse))
        userViewModel.fetchUserDetails(query)

        // Then
        val expectedResult = ResponseStatus.Error(errorCode, errorMessage)
        val actualResult = userViewModel.userDetailsState.value
        assertEquals(expectedResult, actualResult)
    }

}


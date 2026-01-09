package com.naveenprince.github.users.ui

import com.naveenprince.github.core.utils.ResponseStatus
import com.naveenprince.github.users.data.mapper.UserDetailsMapper
import com.naveenprince.github.users.data.source.remote.UserDetailsResponse
import com.naveenprince.github.users.domain.repository.UsersRepository
import com.naveenprince.github.utilities.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert
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
    fun fetchUserDetails_SuccessStatus() = runTest {
        // Given
        val queryUrl = "https://api.github.com/users/naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_details.json")?.readText()
        val userDetailsResponse: UserDetailsResponse =
            Json.decodeFromString<UserDetailsResponse>(jsonString.toString())
        val userDetails = UserDetailsMapper.fromResponse(userDetailsResponse)
        val successState =
            UserDetailsState(userDetails = userDetails, isLoading = false, error = null)

        // When
        Mockito.`when`(usersRepository.fetchUserDetails(queryUrl))
            .thenReturn(flowOf(ResponseStatus.Success(userDetails)))
        userViewModel.onIntent(UserDetailsIntent.FetchUserDetails(queryUrl))

        // Then
        Assert.assertEquals(userViewModel.userDetailsState.value, successState)
    }

    @Test
    fun fetchUserDetails_Error() = runTest {
        // Given
        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error fetching data"
        val errorState =
            UserDetailsState(userDetails = null, isLoading = false, error = errorMessage)

        // When
        Mockito.`when`(usersRepository.fetchUserDetails(query))
            .thenReturn(flowOf(ResponseStatus.Error(errorCode, errorMessage)))
        userViewModel.onIntent(UserDetailsIntent.FetchUserDetails(query))

        // Then
        Assert.assertEquals(userViewModel.userDetailsState.value, errorState)
    }

}
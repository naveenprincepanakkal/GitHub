package com.naveenprince.github.ui.feature.search

import com.google.gson.Gson
import com.naveenprince.github.data.source.remote.search.SearchUsersResponse
import com.naveenprince.github.domain.model.User
import com.naveenprince.github.domain.repository.SearchRepository
import com.naveenprince.github.utilities.MainDispatcherRule
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchUsersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var searchRepository: SearchRepository

    private lateinit var searchViewModel: SearchUsersViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        searchViewModel = SearchUsersViewModel(searchRepository)
    }

    @Test
    fun searchUser_SuccessStatus() = runTest {
        // Given
        val query = "naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_search.json")?.readText()
        val searchUsersResponse: SearchUsersResponse =
            Gson().fromJson(jsonString, SearchUsersResponse::class.java)
        val userList = searchUsersResponse.userList.map { User(it) }
        val successStatus = SearchUsersState(userList = userList, isLoading = false, error = null)

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Success(userList)))
        searchViewModel.searchUser(query)

        // Then
        assertEquals(searchViewModel.searchUsersState.value, successStatus)
    }

    @Test
    fun searchUser_Error() = runTest {
        // Given
        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error fetching data"
        val errorState = SearchUsersState(userList = null, isLoading = false, error = errorMessage)

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Error(errorCode, errorMessage)))
        searchViewModel.searchUser(query)

        // Then
        assertEquals(searchViewModel.searchUsersState.value, errorState)
    }

}
package com.naveenprince.github.viewmodel

import com.google.gson.Gson
import com.naveenprince.github.model.data.SearchUsersResponse
import com.naveenprince.github.model.repository.SearchRepository
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
    @Throws(Exception::class)
    fun searchUser_LoadingStatus() = runTest {
        // Given
        val query = "loading"

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Loading()))
        searchViewModel.searchUser(query)

        // Then
        val expectedResult = ResponseStatus.Loading()
        val actualResult = searchViewModel.userListState.value
        assertEquals(expectedResult, actualResult)
    }

    @Test
    @Throws(Exception::class)
    fun searchUser_SuccessStatus() = runTest {
        // Given
        val query = "naveenprincepanakkal"
        val jsonString = javaClass.getResource("/json/user_search.json")?.readText()
        val searchUsersResponse: SearchUsersResponse =
            Gson().fromJson(jsonString, SearchUsersResponse::class.java)
        val userList = searchUsersResponse.userList

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Success(userList)))
        searchViewModel.searchUser(query)

        // Then
        val expectedResult = ResponseStatus.Success(userList)
        val actualResult = searchViewModel.userListState.value
        assertEquals(expectedResult, actualResult)
    }

    @Test
    @Throws(Exception::class)
    fun searchUser_Error() = runTest {
        // Given
        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error fetching data"
        val errorResponse = ResponseStatus.Error(errorCode, errorMessage)

        // When
        Mockito.`when`(searchRepository.searchUsers(query)).thenReturn(flowOf(errorResponse))
        searchViewModel.searchUser(query)

        // Then
        val expectedResult = ResponseStatus.Error(errorCode, errorMessage)
        val actualResult = searchViewModel.userListState.value
        assertEquals(expectedResult, actualResult)
    }

}
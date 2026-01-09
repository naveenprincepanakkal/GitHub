package com.naveenprince.github.search.ui

import com.naveenprince.github.core.utils.ResponseStatus
import com.naveenprince.github.search.data.mapper.UserMapper
import com.naveenprince.github.search.data.source.remote.SearchUsersResponse
import com.naveenprince.github.search.domain.repository.SearchRepository
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
            Json.decodeFromString<SearchUsersResponse>(jsonString.toString())
        val userList = searchUsersResponse.userList.map { UserMapper.fromResponse(it) }
        val successStatus = SearchUsersState(
            userList = userList,
            isLoading = false,
            error = null,
            searchQuery = query
        )

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Success(userList)))
        searchViewModel.onIntent(SearchUsersIntent.SearchUsers(query))

        // Then
        Assert.assertEquals(searchViewModel.searchUsersState.value, successStatus)
    }

    @Test
    fun searchUser_Error() = runTest {
        // Given
        val query = "InvalidQuery"
        val errorCode = 403
        val errorMessage = "Error fetching data"
        val errorState = SearchUsersState(
            userList = null,
            isLoading = false,
            error = errorMessage,
            searchQuery = query
        )

        // When
        Mockito.`when`(searchRepository.searchUsers(query))
            .thenReturn(flowOf(ResponseStatus.Error(errorCode, errorMessage)))
        searchViewModel.onIntent(SearchUsersIntent.SearchUsers(query))

        // Then
        Assert.assertEquals(searchViewModel.searchUsersState.value, errorState)
    }

}
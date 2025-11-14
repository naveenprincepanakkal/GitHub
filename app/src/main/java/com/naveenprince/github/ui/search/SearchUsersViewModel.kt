package com.naveenprince.github.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.github.domain.search.repository.SearchRepository
import com.naveenprince.github.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for handling search user queries
 *
 * Created by Naveen.
 */
@HiltViewModel
class SearchUsersViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var _searchUsersState = MutableStateFlow(SearchUsersState())
    val searchUsersState: StateFlow<SearchUsersState> = _searchUsersState.asStateFlow()

    private var serviceJob: Job? = null

    fun onIntent(intent: SearchUsersIntent) {
        when (intent) {
            is SearchUsersIntent.SearchUsers -> searchUsers(intent.query)
        }
    }

    private fun searchUsers(query: String) {
        serviceJob?.cancel()
        serviceJob = viewModelScope.launch {
            _searchUsersState.update { state ->
                state.copy(
                    searchQuery = query,
                    isLoading = true,
                    error = null
                )
            }
            searchRepository.searchUsers(_searchUsersState.value.searchQuery).collect {
                when (it) {
                    is ResponseStatus.Success -> {
                        _searchUsersState.update { state ->
                            state.copy(
                                userList = it.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is ResponseStatus.Error -> {
                        _searchUsersState.update { state ->
                            state.copy(
                                userList = null,
                                isLoading = false,
                                error = it.message
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        serviceJob?.cancel()
    }

}
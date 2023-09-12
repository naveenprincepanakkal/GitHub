package com.naveenprince.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.github.model.data.User
import com.naveenprince.github.model.repository.SearchRepository
import com.naveenprince.github.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private var _userListState: MutableStateFlow<ResponseStatus<List<User>>> =
        MutableStateFlow(ResponseStatus.Empty)
    val userListState: StateFlow<ResponseStatus<List<User>>> = _userListState

    fun searchUser(query: String) {
        _userListState.value = ResponseStatus.Loading()
        viewModelScope.launch {
            searchRepository.searchUsers(query).collect {
                _userListState.value = it
            }
        }
    }

}
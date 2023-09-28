package com.naveenprince.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.github.model.data.UserDetails
import com.naveenprince.github.model.repository.UsersRepository
import com.naveenprince.github.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for handling user detail queries
 *
 * Created by Naveen.
 */
@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private var _userDetailsState: MutableStateFlow<ResponseStatus<UserDetails>> =
        MutableStateFlow(ResponseStatus.Empty)
    val userDetailsState: StateFlow<ResponseStatus<UserDetails>> = _userDetailsState

    fun fetchUserDetails(url: String) {
        _userDetailsState.value = ResponseStatus.Loading()
        viewModelScope.launch {
            usersRepository.fetchUserDetails(url).collect {
                _userDetailsState.value = it
            }
        }
    }
}

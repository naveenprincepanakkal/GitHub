package com.naveenprince.github.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.github.domain.users.repository.UsersRepository
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
 * ViewModel class for handling user detail queries
 *
 * Created by Naveen.
 */
@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private var _userDetailsState = MutableStateFlow(UserDetailsState())
    val userDetailsState: StateFlow<UserDetailsState> = _userDetailsState.asStateFlow()

    private var serviceJob: Job? = null

    fun fetchUserDetails(url: String) {
        serviceJob?.cancel()
        serviceJob = viewModelScope.launch {
            _userDetailsState.update { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }
            usersRepository.fetchUserDetails(url).collect {
                when (it) {
                    is ResponseStatus.Success -> {
                        _userDetailsState.update { state ->
                            state.copy(
                                userDetails = it.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is ResponseStatus.Error -> {
                        _userDetailsState.update { state ->
                            state.copy(
                                userDetails = null,
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

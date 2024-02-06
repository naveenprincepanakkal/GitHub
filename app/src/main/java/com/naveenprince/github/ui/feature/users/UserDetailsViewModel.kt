package com.naveenprince.github.ui.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.github.domain.repository.UsersRepository
import com.naveenprince.github.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var _userDetailsState = MutableStateFlow(UserDetailsState())
    val userDetailsState: StateFlow<UserDetailsState> = _userDetailsState

    private var serviceJob: Job? = null

    fun fetchUserDetails(url: String) {
        serviceJob?.cancel()
        serviceJob = viewModelScope.launch {
            _userDetailsState.value = _userDetailsState.value.copy(
                isLoading = true,
                error = null
            )
            usersRepository.fetchUserDetails(url).collect {
                when (it) {
                    is ResponseStatus.Success -> {
                        _userDetailsState.value = _userDetailsState.value.copy(
                            userDetails = it.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is ResponseStatus.Error -> {
                        _userDetailsState.value = _userDetailsState.value.copy(
                            userDetails = null,
                            isLoading = false,
                            error = it.message
                        )
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

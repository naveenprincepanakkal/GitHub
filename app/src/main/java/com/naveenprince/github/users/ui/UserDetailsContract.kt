package com.naveenprince.github.users.ui

import com.naveenprince.github.users.domain.model.UserDetails

/**
 * Created by Naveen.
 */
// State of the UI
data class UserDetailsState(
    val userDetails: UserDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

// Events triggered from UI
sealed interface UserDetailsIntent {
    data class FetchUserDetails(val url: String): UserDetailsIntent
}

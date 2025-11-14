package com.naveenprince.github.ui.users

import com.naveenprince.github.domain.users.model.UserDetails

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

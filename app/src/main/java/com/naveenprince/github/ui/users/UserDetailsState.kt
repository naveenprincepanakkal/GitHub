package com.naveenprince.github.ui.users

import com.naveenprince.github.domain.users.model.UserDetails

/**
 * Created by Naveen.
 */
data class UserDetailsState(
    val userDetails: UserDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
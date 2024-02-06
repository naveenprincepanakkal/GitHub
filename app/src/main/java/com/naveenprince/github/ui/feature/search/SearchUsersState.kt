package com.naveenprince.github.ui.feature.search

import com.naveenprince.github.domain.model.User

/**
 * Created by Naveen.
 */
data class SearchUsersState(
    val userList: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
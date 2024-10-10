package com.naveenprince.github.ui.search

import com.naveenprince.github.domain.search.model.User

/**
 * Created by Naveen.
 */
data class SearchUsersState(
    val userList: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)
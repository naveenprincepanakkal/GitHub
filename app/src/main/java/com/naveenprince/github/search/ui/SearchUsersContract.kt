package com.naveenprince.github.search.ui

import com.naveenprince.github.search.domain.model.User

data class SearchUsersState(
    val userList: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)

sealed interface SearchUsersIntent {
    data class SearchUsers(val query: String): SearchUsersIntent
}


package com.naveenprince.github.domain.users.model

/**
 * Created by Naveen.
 */
data class UserDetails(
    val avatarUrl: String,
    val followers: Int,
    val login: String,
    val publicRepos: Int,
)

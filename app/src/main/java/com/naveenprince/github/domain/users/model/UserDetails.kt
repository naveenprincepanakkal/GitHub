package com.naveenprince.github.domain.users.model

import com.naveenprince.github.data.users.source.remote.UserDetailsResponse

/**
 * Created by Naveen.
 */
data class UserDetails(
    val avatarUrl: String,
    val followers: Int,
    val login: String,
    val publicRepos: Int,
) {
    constructor(userDetailsResponse: UserDetailsResponse) : this(
        userDetailsResponse.avatarUrl,
        userDetailsResponse.followers,
        userDetailsResponse.login,
        userDetailsResponse.publicRepos,
    )
}

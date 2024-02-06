package com.naveenprince.github.domain.model

import com.naveenprince.github.data.source.remote.users.UserDetailsResponse

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

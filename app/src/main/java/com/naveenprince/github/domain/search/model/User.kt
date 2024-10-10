package com.naveenprince.github.domain.search.model

import com.naveenprince.github.data.search.source.remote.UserResponse

/**
 * Created by Naveen.
 */
data class User(
    val avatarUrl: String,
    val login: String,
    val url: String,
) {
    constructor(userResponse: UserResponse) : this(
        userResponse.avatarUrl,
        userResponse.login,
        userResponse.url,
    )
}



package com.naveenprince.github.domain.model

import com.naveenprince.github.data.source.remote.search.UserResponse

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



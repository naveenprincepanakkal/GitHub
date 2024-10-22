package com.naveenprince.github.data.search.mapper

import com.naveenprince.github.data.search.source.remote.UserResponse
import com.naveenprince.github.domain.search.model.User

object UserMapper {

    fun fromResponse(userResponse: UserResponse) =
        User(
            userResponse.avatarUrl,
            userResponse.login,
            userResponse.url,
        )

}

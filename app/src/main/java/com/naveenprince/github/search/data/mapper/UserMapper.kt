package com.naveenprince.github.search.data.mapper

import com.naveenprince.github.search.data.source.remote.UserResponse
import com.naveenprince.github.search.domain.model.User

object UserMapper {

    fun fromResponse(userResponse: UserResponse) =
        User(
            userResponse.avatarUrl,
            userResponse.login,
            userResponse.url,
        )

}

package com.naveenprince.github.users.data.mapper

import com.naveenprince.github.users.data.source.remote.UserDetailsResponse
import com.naveenprince.github.users.domain.model.UserDetails

object UserDetailsMapper {

    fun fromResponse(userDetailsResponse: UserDetailsResponse) =
        UserDetails(
            userDetailsResponse.avatarUrl,
            userDetailsResponse.followers,
            userDetailsResponse.login,
            userDetailsResponse.publicRepos,
        )

}

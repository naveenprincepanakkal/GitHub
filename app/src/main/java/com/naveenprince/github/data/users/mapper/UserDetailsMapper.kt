package com.naveenprince.github.data.users.mapper

import com.naveenprince.github.data.users.source.remote.UserDetailsResponse
import com.naveenprince.github.domain.users.model.UserDetails

object UserDetailsMapper {

    fun fromResponse(userDetailsResponse: UserDetailsResponse) =
        UserDetails(
            userDetailsResponse.avatarUrl,
            userDetailsResponse.followers,
            userDetailsResponse.login,
            userDetailsResponse.publicRepos,
        )

}

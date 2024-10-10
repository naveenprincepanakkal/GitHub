package com.naveenprince.github.domain.users.repository

import com.naveenprince.github.domain.users.model.UserDetails
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRepository {

    /**
     * Fetching user details
     */
    fun fetchUserDetails(url: String): Flow<ResponseStatus<UserDetails>>

}

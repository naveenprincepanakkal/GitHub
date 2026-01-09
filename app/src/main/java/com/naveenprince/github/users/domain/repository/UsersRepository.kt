package com.naveenprince.github.users.domain.repository

import com.naveenprince.github.core.utils.ResponseStatus
import com.naveenprince.github.users.domain.model.UserDetails
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
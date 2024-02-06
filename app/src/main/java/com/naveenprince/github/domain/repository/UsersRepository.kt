package com.naveenprince.github.domain.repository

import com.naveenprince.github.domain.model.UserDetails
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

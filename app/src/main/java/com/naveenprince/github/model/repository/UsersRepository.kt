package com.naveenprince.github.model.repository

import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRepository {

    /**
     * Fetching user details
     */
    fun fetchUserDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>>

}

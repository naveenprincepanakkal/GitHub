package com.naveenprince.github.model.repository

import com.naveenprince.github.model.data.User
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface SearchRepository {

    /**
     * Search users from the given query
     */
    fun searchUsers(query: String): Flow<ResponseStatus<List<User>>>

}

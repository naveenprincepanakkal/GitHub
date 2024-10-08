package com.naveenprince.github.domain.search.repository

import com.naveenprince.github.domain.search.model.User
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

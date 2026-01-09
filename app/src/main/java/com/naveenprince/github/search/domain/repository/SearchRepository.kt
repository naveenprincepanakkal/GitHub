package com.naveenprince.github.search.domain.repository

import com.naveenprince.github.core.utils.ResponseStatus
import com.naveenprince.github.search.domain.model.User
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
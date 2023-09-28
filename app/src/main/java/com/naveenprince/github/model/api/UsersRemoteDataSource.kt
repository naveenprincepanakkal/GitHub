package com.naveenprince.github.model.api

import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRemoteDataSource {

    fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>>

}

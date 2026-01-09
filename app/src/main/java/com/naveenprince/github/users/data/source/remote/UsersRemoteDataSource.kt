package com.naveenprince.github.users.data.source.remote

import com.naveenprince.github.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRemoteDataSource {

    fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>>

}

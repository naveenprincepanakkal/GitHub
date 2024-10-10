package com.naveenprince.github.data.users.source.remote

import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRemoteDataSource {

    fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>>

}

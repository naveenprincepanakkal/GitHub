package com.naveenprince.github.data.source.remote.users

import com.naveenprince.github.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface UsersRemoteDataSource {

    fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>>

}

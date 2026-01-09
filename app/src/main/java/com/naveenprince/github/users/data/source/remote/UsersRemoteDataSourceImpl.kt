package com.naveenprince.github.users.data.source.remote

import com.naveenprince.github.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Naveen.
 */
class UsersRemoteDataSourceImpl @Inject constructor(
    private val usersService: UsersService
) : UsersRemoteDataSource {

    override fun userDetails(url: String): Flow<ResponseStatus<UserDetailsResponse>> = flow {

        try {
            emit(ResponseStatus.Success(data = usersService.userDetails(url)))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(ResponseStatus.Error(message = "Network Error"))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ResponseStatus.Error(message = "HTTP Error"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseStatus.Error(message = e.message ?: "An unknown error occurred."))
        }
    }

}

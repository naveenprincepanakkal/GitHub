package com.naveenprince.github.search.data.source.remote

import com.naveenprince.github.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Data source class for services
 *
 * Created by Naveen.
 */
class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRemoteDataSource {

    override fun searchUsers(query: String): Flow<ResponseStatus<SearchUsersResponse>> = flow {

        try {
            emit(ResponseStatus.Success(data = searchService.searchUsers(query)))
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
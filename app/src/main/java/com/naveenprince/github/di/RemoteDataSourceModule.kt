package com.naveenprince.github.di

import com.naveenprince.github.data.source.remote.search.SearchRemoteDataSource
import com.naveenprince.github.data.source.remote.search.SearchRemoteDataSourceImpl
import com.naveenprince.github.data.source.remote.search.SearchService
import com.naveenprince.github.data.source.remote.users.UsersRemoteDataSource
import com.naveenprince.github.data.source.remote.users.UsersRemoteDataSourceImpl
import com.naveenprince.github.data.source.remote.users.UsersService
import com.naveenprince.github.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provider class for dependency injection of Network Module
 *
 * Created by Naveen.
 */
@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(searchService: SearchService): SearchRemoteDataSource =
        SearchRemoteDataSourceImpl(searchService)

    @Singleton
    @Provides
    fun providerUsersRemoteDataSource(usersService: UsersService): UsersRemoteDataSource =
        UsersRemoteDataSourceImpl(usersService)

    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Singleton
    @Provides
    fun provideUsersService(retrofit: Retrofit): UsersService =
        retrofit.create(UsersService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
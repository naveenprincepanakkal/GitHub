package com.naveenprince.github.di

import com.naveenprince.github.data.source.remote.search.SearchRemoteDataSource
import com.naveenprince.github.data.source.remote.users.UsersRemoteDataSource
import com.naveenprince.github.domain.repository.SearchRepository
import com.naveenprince.github.data.repository.SearchRepositoryImpl
import com.naveenprince.github.domain.repository.UsersRepository
import com.naveenprince.github.data.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provider class for dependency injection of Repository Module
 *
 * Created by Naveen.
 */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(remoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun provideUsersRepository(remoteDataSource: UsersRemoteDataSource): UsersRepository =
        UsersRepositoryImpl(remoteDataSource)

}
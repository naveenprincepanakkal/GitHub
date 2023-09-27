package com.naveenprince.github.di

import com.naveenprince.github.model.api.SearchRemoteDataSource
import com.naveenprince.github.model.api.UsersRemoteDataSource
import com.naveenprince.github.model.repository.SearchRepository
import com.naveenprince.github.model.repository.SearchRepositoryImpl
import com.naveenprince.github.model.repository.UsersRepository
import com.naveenprince.github.model.repository.UsersRepositoryImpl
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
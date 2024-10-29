package com.naveenprince.github.data.di

import com.naveenprince.github.data.users.repository.UsersRepositoryImpl
import com.naveenprince.github.data.users.source.remote.UsersRemoteDataSource
import com.naveenprince.github.data.users.source.remote.UsersRemoteDataSourceImpl
import com.naveenprince.github.data.users.source.remote.UsersService
import com.naveenprince.github.domain.users.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Provider class for dependency injection of Repository Module
 *
 * Created by Naveen.
 */
@InstallIn(SingletonComponent::class)
@Module
object UserModule {

    @Singleton
    @Provides
    fun provideUsersRepository(remoteDataSource: UsersRemoteDataSource): UsersRepository =
        UsersRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun providerUsersRemoteDataSource(usersService: UsersService): UsersRemoteDataSource =
        UsersRemoteDataSourceImpl(usersService)

    @Singleton
    @Provides
    fun provideUsersService(retrofit: Retrofit): UsersService =
        retrofit.create(UsersService::class.java)

}
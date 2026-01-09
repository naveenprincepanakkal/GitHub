package com.naveenprince.github.users.data.di

import com.naveenprince.github.users.data.repository.UsersRepositoryImpl
import com.naveenprince.github.users.data.source.remote.UsersRemoteDataSource
import com.naveenprince.github.users.data.source.remote.UsersRemoteDataSourceImpl
import com.naveenprince.github.users.data.source.remote.UsersService
import com.naveenprince.github.users.domain.repository.UsersRepository
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
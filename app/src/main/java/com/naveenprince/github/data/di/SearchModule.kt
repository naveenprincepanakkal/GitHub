package com.naveenprince.github.data.di

import com.naveenprince.github.data.search.repository.SearchRepositoryImpl
import com.naveenprince.github.data.search.source.remote.SearchRemoteDataSource
import com.naveenprince.github.data.search.source.remote.SearchRemoteDataSourceImpl
import com.naveenprince.github.data.search.source.remote.SearchService
import com.naveenprince.github.domain.search.repository.SearchRepository
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
object SearchModule {

    @Singleton
    @Provides
    fun provideSearchRepository(remoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(searchService: SearchService): SearchRemoteDataSource =
        SearchRemoteDataSourceImpl(searchService)

    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)
}
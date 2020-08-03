package com.example.hiltpractice.di

import com.example.hiltpractice.local.BlogDao
import com.example.hiltpractice.local.CacheMapper
import com.example.hiltpractice.remote.BlogRetrofit
import com.example.hiltpractice.remote.NetworkMapper
import com.example.hiltpractice.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}
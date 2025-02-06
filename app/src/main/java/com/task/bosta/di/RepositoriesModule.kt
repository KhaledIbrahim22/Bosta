package com.task.bosta.di

import com.task.bosta.data.api.ApiService
import com.task.bosta.data.repository.CitiesRepositoryImpl
import com.task.bosta.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideForecastRepository(apiService: ApiService): CitiesRepository {
        return CitiesRepositoryImpl(apiService)
    }
}
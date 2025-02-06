package com.task.bosta.di

import com.task.bosta.domain.repository.CitiesRepository
import com.task.bosta.domain.usecase.GetCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCitiesUseCase(repo: CitiesRepository): GetCitiesUseCase {
        return GetCitiesUseCase(repo)
    }
}
package com.task.bosta.di

import com.task.bosta.data.api.ApiService
import com.task.bosta.utils.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.instance
    }
}
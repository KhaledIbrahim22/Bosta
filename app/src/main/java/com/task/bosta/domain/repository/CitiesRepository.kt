package com.task.bosta.domain.repository

import com.task.bosta.data.model.CityModel
import com.task.bosta.utils.network.State
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    suspend fun getCities(): Flow<State<List<CityModel>>>
}
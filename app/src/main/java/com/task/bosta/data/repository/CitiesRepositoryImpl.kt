package com.task.bosta.data.repository

import com.task.bosta.data.api.ApiService
import com.task.bosta.data.model.CityModel
import com.task.bosta.domain.repository.CitiesRepository
import com.task.bosta.utils.network.State
import com.task.bosta.utils.network.makeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(private val apiService: ApiService) : CitiesRepository {
    override suspend fun getCities(): Flow<State<List<CityModel>>> = makeRequest { apiService.getAllCitiesAndDistricts() }
}
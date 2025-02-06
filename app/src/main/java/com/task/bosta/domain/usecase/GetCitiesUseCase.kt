package com.task.bosta.domain.usecase

import com.task.bosta.data.model.CityModel
import com.task.bosta.domain.repository.CitiesRepository
import com.task.bosta.utils.network.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repo: CitiesRepository) {
    suspend operator fun invoke(): Flow<State<List<CityModel>>> = repo.getCities()
}
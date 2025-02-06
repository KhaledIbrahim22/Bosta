package com.task.bosta.data.api

import com.task.bosta.data.model.BaseResponse
import com.task.bosta.data.model.CityModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cities/getAllDistricts")
    suspend fun getAllCitiesAndDistricts(
        @Query("countryId") countryId: String = "60e4482c7cb7d4bc4849c4d5"
    ): BaseResponse<List<CityModel>>
}
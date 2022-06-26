package com.ruben.location.domain

import com.ruben.components_data.util.ApiResult
import com.ruben.entity.local.City

interface CityUseCase {
  suspend fun getCities(textInput: String = ""): ApiResult<List<City>>

  suspend fun saveCity(city: City?)
}

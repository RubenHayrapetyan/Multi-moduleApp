package com.ruben.location.domain

import com.ruben.components_data.util.ApiResult
import com.ruben.entity.local.Country

interface CountryUseCase {
  suspend fun getCountries(textInput: String = ""): ApiResult<List<Country>>

  suspend fun saveCountry(country: Country)
}

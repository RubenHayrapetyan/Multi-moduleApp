package com.ruben.location.data

import com.ruben.components_data.util.ApiResult
import com.ruben.entity.remote.ApiCity
import com.ruben.entity.remote.ApiCountry

interface LocationRepository {
  suspend fun getCountries(token: String, locale: String): ApiResult<List<ApiCountry>>

  suspend fun getCities(token: String, locale: String, country: Int): ApiResult<List<ApiCity>>
}
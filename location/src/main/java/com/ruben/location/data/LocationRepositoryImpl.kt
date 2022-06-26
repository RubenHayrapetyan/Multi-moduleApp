package com.ruben.location.data

import com.ruben.components_data.util.ApiResult
import com.ruben.components_data.util.makeApiCall
import com.ruben.entity.remote.ApiCity
import com.ruben.entity.remote.ApiCountry
import com.ruben.location.data.remote.LocationService
import javax.inject.Inject

internal class LocationRepositoryImpl @Inject constructor(
  private val locationService: LocationService
) : LocationRepository {

  override suspend fun getCountries(token: String, locale: String): ApiResult<List<ApiCountry>> =
    makeApiCall {
      locationService.getCountries(token = token, locale = locale)
    }

  override suspend fun getCities(
    token: String,
    locale: String,
    country: Int
  ): ApiResult<List<ApiCity>> = makeApiCall {
    locationService.getCities(token = token, locale = locale, country = country)
  }
}
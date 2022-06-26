package com.ruben.location.domain

import com.ruben.components_data.datastore.PreferencesService
import com.ruben.components_data.util.ApiResult
import com.ruben.entity.local.City
import com.ruben.entity.local.toCity
import com.ruben.location.data.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CityUseCaseImpl @Inject constructor(
  private val locationRepository: LocationRepository,
  private val preferencesService: PreferencesService,
  private val ioDispatcher: CoroutineDispatcher,
) : CityUseCase {

  override suspend fun getCities(textInput: String): ApiResult<List<City>> = withContext(ioDispatcher) {
    val token = preferencesService.getToken().firstOrNull()
      ?: return@withContext ApiResult.Error(e = NullPointerException("Token is null"))

    val locale = preferencesService.getCurrentLocale().firstOrNull()
      ?: return@withContext ApiResult.Error(e = NullPointerException("Culture is null"))

    val country = preferencesService.getCurrentCountry().firstOrNull()
      ?: return@withContext ApiResult.Error(e = NullPointerException("Culture is null"))

    locationRepository.getCities(
      token = "Bearer $token",
      locale = locale,
      country = country.id,
    ).let { result ->
      when (result) {
        is ApiResult.Success -> {
          val data = if (textInput.isEmpty()) {
            listOf(City.EMPTY) + result.data.map { apiCity -> apiCity.toCity() }
          } else {
            result.data
              .map { apiCity -> apiCity.toCity() }
              .filter { city ->
                city.name.contains(other = textInput, ignoreCase = true)
              }
          }
          ApiResult.Success(data  = data)
        }
        is ApiResult.Error -> result
      }
    }
  }

  override suspend fun saveCity(city: City?) = withContext(ioDispatcher) {
    if (city != null) {
      preferencesService.setCurrentCity(city = city)
    }else {
      preferencesService.deleteSavedCity()
    }
  }
}

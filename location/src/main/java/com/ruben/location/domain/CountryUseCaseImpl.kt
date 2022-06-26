package com.ruben.location.domain

import com.ruben.components_data.datastore.PreferencesService
import com.ruben.components_data.util.ApiResult
import com.ruben.entity.local.Country
import com.ruben.entity.local.toCountry
import com.ruben.location.data.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CountryUseCaseImpl @Inject constructor(
  private val locationRepository: LocationRepository,
  private val preferencesService: PreferencesService,
  private val ioDispatcher: CoroutineDispatcher,
) : CountryUseCase {
  override suspend fun getCountries(textInput: String): ApiResult<List<Country>> = withContext(ioDispatcher) {
    val token = preferencesService.getToken().firstOrNull()
      ?: return@withContext ApiResult.Error(e = NullPointerException("Token is null"))

    val locale = preferencesService.getCurrentLocale().firstOrNull()
      ?: return@withContext ApiResult.Error(e = NullPointerException("Culture is null"))

    locationRepository.getCountries(token = "Bearer $token", locale = locale).let { result ->
      when (result) {
        is ApiResult.Success -> ApiResult.Success(
          result.data
            .map { apiCountry -> apiCountry.toCountry()}
            .filter { country ->
            country.name.contains(other = textInput, ignoreCase = true)
          }
        )
        is ApiResult.Error -> result
      }
    }
  }

  override suspend fun saveCountry(country: Country) = withContext(ioDispatcher) {
    preferencesService.setCurrentCountry(country = country)
  }
}

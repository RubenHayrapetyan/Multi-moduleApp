package com.ruben.components_data.datastore

import com.ruben.entity.local.City
import com.ruben.entity.local.Country
import kotlinx.coroutines.flow.Flow

interface PreferencesService {
  suspend fun setUUID(uuid: String)

  fun getUUID(): Flow<String>

  suspend fun deleteSavedCity()

  suspend fun setCurrentLocale(locale: String)

  fun getCurrentLocale(): Flow<String>

  fun getCurrentLocaleBlocking(): String

  suspend fun setCurrentCountry(country: Country)

  fun getCurrentCountry(): Flow<Country?>

  suspend fun setCurrentCity(city: City)

  fun getCurrentCity(): Flow<City?>

  suspend fun setToken(token: String)

  fun getToken(): Flow<String>

  suspend fun setRefreshToken(token: String)

  fun getRefreshToken(): Flow<String>
}
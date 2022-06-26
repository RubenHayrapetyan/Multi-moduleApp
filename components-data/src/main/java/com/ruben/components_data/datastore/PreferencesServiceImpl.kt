package com.ruben.components_data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.google.gson.Gson
import com.ruben.components_data.getData
import com.ruben.components_data.setData
import com.ruben.constants.*
import com.ruben.entity.local.City
import com.ruben.entity.local.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class PreferencesServiceImpl @Inject constructor(
  private val preferences: DataStore<Preferences>,
  private val gson: Gson,
) : PreferencesService {
  override suspend fun setUUID(uuid: String) {
    preferences.setData(key = PREF_UUID, value = uuid)
  }

  override fun getUUID(): Flow<String> =
    preferences.getData(key = PREF_UUID, defaultValue = "")

  override suspend fun deleteSavedCity() {
    preferences.edit {
      it.remove(PREF_CITY)
    }
  }

  override suspend fun setCurrentLocale(locale: String) {
    preferences.setData(key = PREF_LOCALE, value = locale)
  }

  override fun getCurrentLocale(): Flow<String> =
    preferences.getData(key = PREF_LOCALE, defaultValue = "")

  override fun getCurrentLocaleBlocking(): String = runBlocking {
    preferences.getData(key = PREF_LOCALE, defaultValue = "").first()
  }

  override suspend fun setToken(token: String) {
    preferences.setData(key = PREF_TOKEN, value = token)
  }

  override fun getToken(): Flow<String> =
    preferences.getData(key = PREF_TOKEN, defaultValue = "")

  override suspend fun setRefreshToken(token: String) {
    preferences.setData(key = PREF_REFRESH_TOKEN, value = token)
  }

  override fun getRefreshToken(): Flow<String> =
    preferences.getData(key = PREF_REFRESH_TOKEN, defaultValue = "")

  override suspend fun setCurrentCountry(country: Country) {
    val countryJson = gson.toJson(country, Country::class.java)
    preferences.setData(key = PREF_COUNTRY, value = countryJson)
  }

  override fun getCurrentCountry(): Flow<Country?> =
    preferences.getData(key = PREF_COUNTRY, defaultValue = "").map { countryJson ->
      if (countryJson.isEmpty()) {
        null
      } else {
        gson.fromJson(countryJson, Country::class.java)
      }
    }

  override suspend fun setCurrentCity(city: City) {
    val cityJson = gson.toJson(city, City::class.java)
    preferences.setData(key = PREF_CITY, value = cityJson)
  }

  override fun getCurrentCity(): Flow<City?> =
    preferences.getData(key = PREF_CITY, defaultValue = "").map { cityJson ->
      if (cityJson.isEmpty()) {
        null
      } else {
        gson.fromJson(cityJson, City::class.java)
      }
    }

  companion object {
    private val PREF_UUID = stringPreferencesKey(Constants.PREF_UUID)
    private val PREF_LOCALE = stringPreferencesKey(Constants.PREF_LOCALE_KEY)
    private val PREF_TOKEN = stringPreferencesKey(Constants.PREF_TOKEN_KEY)
    private val PREF_REFRESH_TOKEN = stringPreferencesKey(Constants.PREF_REFRESH_TOKEN_KEY)
    private val PREF_COUNTRY = stringPreferencesKey(Constants.PREF_COUNTRY_KEY)
    private val PREF_CITY = stringPreferencesKey(Constants.PREF_CITY_KEY)
  }
}
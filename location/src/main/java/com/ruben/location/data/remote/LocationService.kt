package com.ruben.location.data.remote

import com.ruben.entity.remote.ApiCity
import com.ruben.entity.remote.ApiCountry
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LocationService {
  @GET("/test/country")
  suspend fun getCountries(
    @Header("Authorization") token: String,
    @Query("locale") locale: String,
  ): List<ApiCountry>

  @GET("/test/city")
  suspend fun getCities(
    @Header("Authorization") token: String,
    @Query("locale") locale: String,
    @Query("country") country: Int,
  ): List<ApiCity>
}

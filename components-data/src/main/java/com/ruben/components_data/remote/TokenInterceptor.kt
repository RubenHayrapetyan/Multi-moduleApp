package com.ruben.components_data.remote

import com.google.gson.Gson
import com.ruben.components_data.BuildConfig
import com.ruben.components_data.datastore.PreferencesService
import com.ruben.components_data.util.ApiResult
import com.ruben.components_data.util.makeApiCall
import com.ruben.entity.request.AuthBody
import com.ruben.entity.request.RefreshTokenBody
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
  private val preferencesService: PreferencesService,
  private val converterFactory: GsonConverterFactory,
  private val gson: Gson
) : Interceptor {
  private val retrofit = Retrofit.Builder().apply {
    baseUrl(BuildConfig.apiUrl)
    addConverterFactory(converterFactory)
  }.build()

  override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
    val request: Request = chain.request()

    val response = chain.proceed(request = request)
    if (!response.isSuccessful && response.code == 401) {
      response.close()

      val tokenService: TokenService = retrofit.create(TokenService::class.java)

      val refreshTokenService = RefreshTokenBody(
        token = preferencesService.getToken().firstOrNull() ?: "",
        refreshToken = preferencesService.getRefreshToken().firstOrNull() ?: "",
      )

      val apiToken = when (val refreshTokenResult =
        makeApiCall { tokenService.refreshToken(refreshTokenService) }) {
        is ApiResult.Success -> {
          refreshTokenResult.data
        }
        is ApiResult.Error -> {
          val savedUUID: String = preferencesService.getUUID().first()
          if (savedUUID.isEmpty()) {
            val uuid: String = UUID.randomUUID().toString()
            preferencesService.setUUID(uuid = uuid)
          }
          val authBody = AuthBody(
            appKey = BuildConfig.appKey,
            key = savedUUID,
            language = preferencesService.getCurrentLocale().firstOrNull() ?: "",
            version = BuildConfig.appVersionCode.toString(),
            deviceType = 1,
          )
          tokenService.generateToken(body = authBody)
        }
      }

      preferencesService.setToken(token = apiToken.token)
      preferencesService.setRefreshToken(token = apiToken.refreshToken)

      val newRequest = request.newBuilder()
        .removeHeader("Authorization")
        .addHeader("Authorization", "Bearer " + apiToken.token)
        .build()

      chain.proceed(request = newRequest)
    } else {
      response
    }
  }
}
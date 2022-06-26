package com.ruben.components_data.remote

import com.ruben.entity.remote.ApiToken
import com.ruben.entity.request.AuthBody
import com.ruben.entity.request.RefreshTokenBody
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
  @POST("/test/test")
  suspend fun generateToken(@Body body: AuthBody): ApiToken

  @POST("/test/test")
  suspend fun refreshToken(@Body body: RefreshTokenBody): ApiToken
}

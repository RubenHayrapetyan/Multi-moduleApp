package com.ruben.entity.request

import com.google.gson.annotations.SerializedName

data class RefreshTokenBody(
  @SerializedName("appToken")
  val appToken: String,
  @SerializedName("appRefreshToken")
  val appRefreshToken: String
)

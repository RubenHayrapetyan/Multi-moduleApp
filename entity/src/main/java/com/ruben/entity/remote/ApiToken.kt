package com.ruben.entity.remote

import com.google.gson.annotations.SerializedName

data class ApiToken(
  @SerializedName("appToken")
  val appToken: String,
  @SerializedName("appRefreshToken")
  val appRefreshToken: String
)
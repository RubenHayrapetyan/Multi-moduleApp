package com.ruben.entity.remote

import com.google.gson.annotations.SerializedName

data class ApiCity(
  @SerializedName("cityId")
  val cityId: Int?,
  @SerializedName("cityName")
  val cityName: String?,
  @SerializedName("cityOrder")
  val cityOrder: Int?
)
package com.ruben.entity.request

import com.google.gson.annotations.SerializedName

data class AuthBody(
  @SerializedName("projectKey")
  val projectKey: String,
  @SerializedName("myKey")
  val myKey: String,
  @SerializedName("appLanguage")
  val appLanguage: String,
  @SerializedName("appVersion")
  val appVersion: String,
  @SerializedName("appDeviceType")
  val appDeviceType: Int
)

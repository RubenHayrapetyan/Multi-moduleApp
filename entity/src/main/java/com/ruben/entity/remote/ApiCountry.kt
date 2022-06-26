package com.ruben.entity.remote

import com.google.gson.annotations.SerializedName

data class ApiCountry(
  @SerializedName("countryId")
  val countryId: Int?,
  @SerializedName("countryName")
  val countryName: String?,
  @SerializedName("countryImageUrl")
  val countryImageUrl: String?,
  @SerializedName("countryCode")
  val countryCode: String?,
  @SerializedName("isIndustryEnabled")
  val isIndustryEnabled: Boolean?
)
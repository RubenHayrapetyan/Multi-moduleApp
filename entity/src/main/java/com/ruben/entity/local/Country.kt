package com.ruben.entity.local

import com.ruben.entity.remote.ApiCountry

data class Country(
  val id: Int,
  val name: String,
  val imageUrl: String,
  val code: String,
  val isIndustryEnabled: Boolean
)

fun ApiCountry.toCountry() = Country(
  id = id ?: 0,
  name = name ?: "",
  imageUrl = imageUrl ?: "",
  code = code ?: "",
  isIndustryEnabled = isIndustryEnabled ?: false
)
package com.ruben.entity.local

import com.ruben.entity.remote.ApiCity

data class City(
  val id: Int,
  val name: String,
  val order: Int
) {
  companion object {
    val EMPTY = City(-1, "", -1)
  }
}

fun ApiCity.toCity() = City(
  id = id ?: 0,
  name = name ?: "",
  order = order ?: 0
)
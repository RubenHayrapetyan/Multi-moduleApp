package com.ruben.components_data.util

sealed class ApiResult<out T> {
  data class Success<out T>(val data: T) : ApiResult<T>()
  data class Error(val e: Throwable) : ApiResult<Nothing>()
}
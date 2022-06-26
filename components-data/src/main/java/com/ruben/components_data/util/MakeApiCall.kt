package com.ruben.components_data.util

import android.util.Log

suspend fun <R> makeApiCall(call: suspend () -> R) = try {
  ApiResult.Success(data = call())
} catch (e: Exception) {
  Log.e("MultiModuleApp", "Api Error", e)
  ApiResult.Error(e = e)
}

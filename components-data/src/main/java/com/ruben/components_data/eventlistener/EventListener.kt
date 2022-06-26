package com.ruben.components_data.eventlistener

import kotlinx.coroutines.flow.Flow

interface EventListener {
  suspend fun sendEvent(key: String, value: Any? = null)

  suspend fun onEvent(key: String): Flow<Any?>
}

package com.ruben.components_data.eventlistener

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

internal class EventListenerImpl @Inject constructor() : EventListener {
  private val eventsStack = MutableSharedFlow<Pair<String, Any?>>()

  override suspend fun sendEvent(key: String, value: Any?) {
    eventsStack.emit(value = key to value)
  }

  override suspend fun onEvent(key: String): Flow<Any?> =
    eventsStack.transform { transformation ->
      if (transformation.first == key) {
        emit(value = transformation.second)
      }
    }
}

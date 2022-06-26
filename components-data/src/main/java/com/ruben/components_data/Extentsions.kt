package com.ruben.components_data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

suspend fun <T> DataStore<Preferences>.setData(key: Preferences.Key<T>, value: T) {
  withContext(Dispatchers.IO) {
    updateData { preferences ->
      preferences.toMutablePreferences().apply { set(key = key, value = value) }
    }
  }
}

fun <T> DataStore<Preferences>.getData(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
  data.transform { preferences ->
    val data = preferences[key] ?: defaultValue
    emit(value = data)
  }
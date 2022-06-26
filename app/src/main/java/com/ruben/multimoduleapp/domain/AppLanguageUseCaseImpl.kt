package com.ruben.multimoduleapp.domain

import com.ruben.components_data.datastore.PreferencesService
import javax.inject.Inject

internal class AppLanguageUseCaseImpl @Inject constructor(
  private val preferencesService: PreferencesService
) : AppLanguageUseCase {
  override fun getCurrentLanguageBlocking(): String = preferencesService.getCurrentLocaleBlocking()
}
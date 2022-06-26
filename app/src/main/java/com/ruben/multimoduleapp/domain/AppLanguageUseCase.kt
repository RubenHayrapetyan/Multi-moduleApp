package com.ruben.multimoduleapp.domain

interface AppLanguageUseCase {
  fun getCurrentLanguageBlocking(): String
}

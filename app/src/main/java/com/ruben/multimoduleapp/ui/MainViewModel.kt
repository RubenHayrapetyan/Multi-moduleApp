package com.ruben.multimoduleapp.ui

import androidx.lifecycle.ViewModel
import com.ruben.multimoduleapp.domain.AppLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appLanguageUseCase: AppLanguageUseCase) : ViewModel() {
  private val _currentLanguage = MutableStateFlow(appLanguageUseCase.getCurrentLanguageBlocking())
  val currentLanguage: StateFlow<String> get() = _currentLanguage
}

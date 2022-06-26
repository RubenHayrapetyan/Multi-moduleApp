package com.ruben.location.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.components_data.util.ApiResult
import com.ruben.entity.local.Country
import com.ruben.location.domain.CountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryUseCase: CountryUseCase) : ViewModel() {
  private val countriesFlow = MutableStateFlow(emptyList<Country>())
  val countries: Flow<List<Country>> get() = countriesFlow

  private val countriesErrorFlow = MutableSharedFlow<Unit>()
  val countriesError: Flow<Unit> get() = countriesErrorFlow

  init {
    viewModelScope.launch {
      countryUseCase.getCountries().let { result ->
        when (result) {
          is ApiResult.Success -> countriesFlow.value = result.data
          is ApiResult.Error -> countriesErrorFlow.emit(value = Unit)
        }
      }
    }
  }

  fun saveCountry(country: Country) {
    viewModelScope.launch {
      countryUseCase.saveCountry(country = country)
    }
  }

  fun searchCountries(textInput: String) {
    viewModelScope.launch {
      countryUseCase.getCountries(textInput = textInput).let { result ->
        when (result) {
          is ApiResult.Success -> countriesFlow.value = result.data
          is ApiResult.Error -> countriesErrorFlow.emit(value = Unit)
        }
      }
    }
  }
}

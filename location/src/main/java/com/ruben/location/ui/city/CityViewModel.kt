package com.ruben.location.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ruben.components_data.eventlistener.EventListener
import com.ruben.components_data.eventlistener.Events
import com.ruben.components_data.util.ApiResult
import com.ruben.components_ui.navigation.NavigationHome
import com.ruben.components_ui.navigation.NavigationTutorial
import com.ruben.constants.Constants
import com.ruben.entity.local.City
import com.ruben.location.domain.CityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
  private val cityUseCase: CityUseCase,
  private val router: Router,
  private val eventListener: EventListener,
  @NavigationHome
  private val homeScreen: FragmentScreen,
  @NavigationTutorial
  private val tutorialScreen: FragmentScreen,
) : ViewModel() {
  private val citiesFlow = MutableStateFlow(emptyList<City>())
  val cities: Flow<List<City>> get() = citiesFlow

  private val citiesErrorFlow = MutableSharedFlow<Unit>()
  val citiesError: Flow<Unit> get() = citiesErrorFlow

  init {
    viewModelScope.launch {
      cityUseCase.getCities().let { result ->
        when (result) {
          is ApiResult.Success -> citiesFlow.value = result.data
          is ApiResult.Error -> citiesErrorFlow.emit(value = Unit)
        }
      }
    }
  }

  fun notifyCloseLocationPicker(parent: String, city: City) {
    viewModelScope.launch {
      cityUseCase.saveCity(city = city)
      if (parent == Constants.PARENT_HOME) {
        router.backTo(screen = homeScreen)
        eventListener.sendEvent(Events.EVENT_SHOW_NAV)
      } else {
        router.navigateTo(screen = tutorialScreen)
      }
    }
  }

  fun searchCities(textInput: String) {
    viewModelScope.launch {
      cityUseCase.getCities(textInput = textInput).let { result ->
        when (result) {
          is ApiResult.Success -> citiesFlow.value = result.data
          is ApiResult.Error -> citiesErrorFlow.emit(value = Unit)
        }
      }
    }
  }
}
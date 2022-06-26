package com.ruben.location.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ruben.constants.Constants
import com.ruben.location.ui.city.CityFragment
import com.ruben.location.ui.country.CountryFragment

fun countryPickerScreenFromLocation() = FragmentScreen {
  CountryFragment.newInstance(parent = Constants.PARENT_LOCATION)
}

fun countryPickerScreenForHome() = FragmentScreen {
  CountryFragment.newInstance(parent = Constants.PARENT_HOME)
}

fun cityPickerScreenForLocation() = FragmentScreen {
  CityFragment.newInstance(parent = Constants.PARENT_LOCATION)
}

fun cityPickerScreenForHome() = FragmentScreen {
  CityFragment.newInstance(parent = Constants.PARENT_HOME)
}
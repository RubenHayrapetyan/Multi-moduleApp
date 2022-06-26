package com.ruben.multimoduleapp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.ruben.components_ui.navigation.*
import com.ruben.location.navigation.countryPickerScreenForHome
import com.ruben.location.navigation.countryPickerScreenFromLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
  @Singleton
  @Provides
  fun getCicerone(): Cicerone<Router> = Cicerone.create()
}

@Module
@InstallIn(FragmentComponent::class, ViewModelComponent::class)
object FragmentModule {
  @Provides
  fun getRouter(router: Cicerone<Router>): Router = router.router
}

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
  @Provides
  fun getNavigatorHolder(router: Cicerone<Router>): NavigatorHolder = router.getNavigatorHolder()
}

@Module
@InstallIn(ViewModelComponent::class)
object ScreenModel {
  @Provides
  @NavigationCountryPickerForHome
  fun provideCountryPickerScreenForHome() = countryPickerScreenForHome()

  @Provides
  @NavigationCountryPickerFromLocation
  fun provideCountryPickerScreenForLocation() = countryPickerScreenFromLocation()
}
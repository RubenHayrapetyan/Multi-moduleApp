package com.ruben.location.di

import com.ruben.location.domain.CityUseCase
import com.ruben.location.domain.CityUseCaseImpl
import com.ruben.location.domain.CountryUseCase
import com.ruben.location.domain.CountryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
  @Binds
  abstract fun bindCountryUseCase(useCase: CountryUseCaseImpl): CountryUseCase

  @Binds
  abstract fun bindCityUseCase(useCase: CityUseCaseImpl): CityUseCase
}
package com.ruben.components_data.di

import com.ruben.components_data.service.LocaleService
import com.ruben.components_data.service.LocaleServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class, ActivityComponent::class)
internal abstract class ActivityServiceModule {
  @Binds
  abstract fun bindLocaleService(service: LocaleServiceImpl): LocaleService
}

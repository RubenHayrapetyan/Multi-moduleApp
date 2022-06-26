package com.ruben.components_data.di

import com.ruben.components_data.datastore.PreferencesService
import com.ruben.components_data.datastore.PreferencesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class PreferencesModule {
  @Binds
  abstract fun bindPreferencesService(preferencesServiceImpl: PreferencesServiceImpl): PreferencesService
}

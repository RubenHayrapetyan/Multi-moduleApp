package com.ruben.location.di

import com.ruben.location.data.LocationRepository
import com.ruben.location.data.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
  @Binds
  abstract fun bindLocationRepository(repository: LocationRepositoryImpl): LocationRepository
}

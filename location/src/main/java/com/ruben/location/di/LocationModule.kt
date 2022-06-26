package com.ruben.location.di

import com.ruben.location.data.remote.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object LocationModule {
  @Provides
  fun provideLocationService(retrofit: Retrofit): LocationService = retrofit.create(LocationService::class.java)
}

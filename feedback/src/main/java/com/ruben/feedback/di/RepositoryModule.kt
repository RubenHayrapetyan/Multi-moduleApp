package com.ruben.feedback.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.ruben.feedback.data.FeedbackRepository
import com.ruben.feedback.data.FeedbackRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
  @Binds
  abstract fun bindLocationRepository(repository: FeedbackRepositoryImpl): FeedbackRepository
}
package com.ruben.feedback.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.ruben.feedback.domain.FeedbackUseCase
import com.ruben.feedback.domain.FeedbackUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
  @Binds
  abstract fun bindFeedbackEmojisUseCase(useCase: FeedbackUseCaseImpl): FeedbackUseCase
}
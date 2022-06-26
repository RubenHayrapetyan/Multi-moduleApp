package com.ruben.components_data.di

import com.ruben.components_data.eventlistener.EventListener
import com.ruben.components_data.eventlistener.EventListenerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EventListenerModule {
  @Binds
  @Singleton
  abstract fun bindEventListener(eventListener: EventListenerImpl): EventListener
}
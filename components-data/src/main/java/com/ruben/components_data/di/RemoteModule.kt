package com.ruben.components_data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ruben.components_data.BuildConfig
import com.ruben.components_data.datastore.PreferencesService
import com.ruben.components_data.remote.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object RemoteModule {
  @Provides
  fun provideGson(): Gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    .create()

  @Provides
  fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

  @Provides
  fun provideTokenInterceptor(
    preferencesService: PreferencesService,
    converterFactory: GsonConverterFactory,
    gson: Gson,
  ): TokenInterceptor = TokenInterceptor(
    preferencesService = preferencesService,
    converterFactory = converterFactory,
    gson = gson,
  )

  @Provides
  fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
    readTimeout(1, TimeUnit.MINUTES)
    connectTimeout(1, TimeUnit.MINUTES)
    callTimeout(1, TimeUnit.MINUTES)
    writeTimeout(1, TimeUnit.MINUTES)

    protocols(listOf(Protocol.HTTP_1_1))

    connectionSpecs(
      listOf(
        ConnectionSpec.MODERN_TLS,
        ConnectionSpec.COMPATIBLE_TLS,
        ConnectionSpec.RESTRICTED_TLS,
        ConnectionSpec.CLEARTEXT
      )
    )

    addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
      this.level = HttpLoggingInterceptor.Level.BODY
    })
    addInterceptor(tokenInterceptor)
  }.build()

  @Provides
  fun provideRetrofit(
    converterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient,
  ): Retrofit = Retrofit.Builder().apply {
    baseUrl(BuildConfig.apiUrl)
    addConverterFactory(converterFactory)
    client(okHttpClient)
  }.build()
}

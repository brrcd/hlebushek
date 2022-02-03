package com.example.hlebushek.di

import com.example.hlebushek.TINKOFF_API_V1_URL
import com.example.hlebushek.TINKOFF_API_V2_URL
import com.example.hlebushek.api.TraderApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Reusable
    @Provides
    @Named("V2")
    fun provideV2Api(okHttpClient: OkHttpClient): TraderApi =
        Retrofit.Builder()
            .baseUrl(TINKOFF_API_V2_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TraderApi::class.java)
}
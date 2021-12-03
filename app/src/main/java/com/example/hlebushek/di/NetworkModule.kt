package com.example.hlebushek.di

import com.example.hlebushek.BuildConfig
import com.example.hlebushek.api.TraderApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val origin = chain.request()
            val requestBuilder = origin.newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.TRADER_API}")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()


    @Reusable
    @Provides
    fun provideApi(okHttpClient: OkHttpClient): TraderApi =
        Retrofit.Builder()
            .baseUrl("https://api-invest.tinkoff.ru/openapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TraderApi::class.java)
}
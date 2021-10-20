package com.example.hlebushek.api

import com.example.hlebushek.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ApiUtils {
    private const val traderMainUrlPart = "https://api-invest.tinkoff.ru/openapi"
    private const val traderApi = BuildConfig.TRADER_API
    const val traderUrl = "$traderMainUrlPart/"

    // TODO remove this interceptor
    private val interceptor = HttpLoggingInterceptor()

    fun getTraderHttpClient() = getOkHttpClient(traderApi)

    private fun getOkHttpClient(apiKey: String): OkHttpClient {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val origin = chain.request()
                val requestBuilder = origin.newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()
        return httpClient
    }
}
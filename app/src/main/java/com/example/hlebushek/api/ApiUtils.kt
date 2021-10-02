package com.example.hlebushek.api

import com.example.hlebushek.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ApiUtils {
    private const val baseUrlMainPart = "https://api.themoviedb.org/"
    private const val baseUrlVersion = "3"
    private const val baseUrlApiKey = BuildConfig.SANDBOX_API
    const val baseUrl = "$baseUrlMainPart$baseUrlVersion/"

    // TODO remove this interceptor
    private val interceptor = HttpLoggingInterceptor()

    fun getOkHttpClient(): OkHttpClient {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val origin = chain.request()
                val originUrl = origin.url
                val url = originUrl.newBuilder()
                    .addQueryParameter("api_key", baseUrlApiKey)
                    .build()
                val requestBuilder = origin.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()
        return httpClient
    }
}
package com.example.hlebushek.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TraderRepo {
    val api: TraderApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.traderUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getTraderHttpClient())
            .build()
        adapter.create(TraderApi::class.java)
    }
}
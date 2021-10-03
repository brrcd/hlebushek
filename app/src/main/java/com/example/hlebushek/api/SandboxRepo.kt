package com.example.hlebushek.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SandboxRepo {
    val api: SandboxApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.sandboxUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getSandboxHttpClient())
            .build()
        adapter.create(SandboxApi::class.java)
    }
}
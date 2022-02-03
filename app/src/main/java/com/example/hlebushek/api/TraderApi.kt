package com.example.hlebushek.api

import com.example.hlebushek.READONLY_TOKEN
import com.example.hlebushek.model.remote.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TraderApi {
    @GET("market/stocks")
    fun getListOfStocks(
        @Header("Authorization") token: String = "Bearer $READONLY_TOKEN"
    ): Call<ApiResponse>
}
package com.example.hlebushek.api

import com.example.hlebushek.model.remote.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TraderApi {

    @GET("market/stocks")
    fun getListOfStocks(): Call<ApiResponse>

    @GET("market/candles")
    fun getCandleByFigi(
        @Query("figi") figi: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("interval") interval: String
    ): Call<ApiResponse>

    @GET("market/orderbook")
    fun getOrderbookByFigi(
        @Query("figi") figi: String,
        @Query("depth") depth: Int
    ): Call<ApiResponse>
}
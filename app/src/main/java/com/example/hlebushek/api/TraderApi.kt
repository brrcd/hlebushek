package com.example.hlebushek.api

import com.example.hlebushek.model.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TraderApi {
    @GET("portfolio")
    fun getPortfolio() : Call<ApiResponse>

    @GET("portfolio/currencies")
    fun getPortfolioCurrencies() : Call<ApiResponse>

    @GET("orders")
    fun getOrders() : Call<ApiResponse>

    @POST("orders/cancel")
    fun cancelOrder() : Call<ApiResponse>

    @GET("market/stocks")
    fun getListOfStocks() : Call<ApiResponse>

    @GET("market/bonds")
    fun getListOfBonds() : Call<ApiResponse>

    @GET("market/etfs")
    fun getListOfETFS() : Call<ApiResponse>

    @GET("market/currencies")
    fun getListOfCurrencyPairs() : Call<ApiResponse>

    @GET("market/search/by-figi")
    fun searchByFigi() : Call<ApiResponse>

    @GET("market/search/by-ticker")
    fun searchByTicker() : Call<ApiResponse>

    @GET("operations")
    fun getListOfOperations() : Call<ApiResponse>
}
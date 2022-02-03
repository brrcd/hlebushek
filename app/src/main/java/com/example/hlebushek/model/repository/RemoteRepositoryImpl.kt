package com.example.hlebushek.model.repository

import com.example.hlebushek.api.TraderApi
import com.example.hlebushek.model.remote.ApiResponse
import javax.inject.Inject

interface RemoteRepository {
    fun getListOfStockMarket(): ApiResponse?
    fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse?
    fun getOrderbook(figi: String, depth: Int): ApiResponse?
}

class RemoteRepositoryImpl
@Inject  constructor(
    @Named("V2")
    private val api: TraderApi
) : RemoteRepository {
}
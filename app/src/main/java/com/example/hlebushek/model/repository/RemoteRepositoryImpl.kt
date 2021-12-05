package com.example.hlebushek.model.repository

import com.example.hlebushek.api.TraderApi
import com.example.hlebushek.model.remote.ApiResponse
import javax.inject.Inject

class RemoteRepositoryImpl
@Inject constructor(
    private val api: TraderApi
): RemoteRepository {

    override fun getListOfStockMarket(): ApiResponse? {
        return api.getListOfStocks().execute().body()
    }

    override fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse? {
        return api.getCandleByFigi(figi, from, to, interval).execute().body()
    }

    override fun getOrderbook(figi: String, depth: Int): ApiResponse? {
        return api.getOrderbook(figi, depth).execute().body()
    }
}
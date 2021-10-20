package com.example.hlebushek.model.repository

import com.example.hlebushek.api.TraderRepo
import com.example.hlebushek.model.remote.ApiResponse

class RemoteRepositoryImpl : RemoteRepository {
    override fun getPortfolio(): ApiResponse? {
        return TraderRepo.api.getPortfolio().execute().body()
    }

    override fun getListOfStockMarket(): ApiResponse? {
        return TraderRepo.api.getListOfStocks().execute().body()
    }

    override fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse? {
        return TraderRepo.api.getCandleByFigi(figi, from, to, interval).execute().body()
    }
}
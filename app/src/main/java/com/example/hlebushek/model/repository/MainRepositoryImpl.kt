package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.ApiResponse
import com.example.hlebushek.model.remote.Stock
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MainRepository {
    override fun getListOfStockMarket(): ApiResponse? =
        remoteRepository.getListOfStockMarket()


    override fun getCandleByFigi(
        figi: String,
        from: String,
        to: String,
        interval: String
    ): ApiResponse? =
        remoteRepository.getCandleByFigi(figi, from, to, interval)


    override fun getOrderbook(figi: String, depth: Int): ApiResponse? =
        remoteRepository.getOrderbook(figi, depth)


    override fun addStockToCurrentTrade(stock: Stock) =
        localRepository.addStockToCurrentTrade(stock)

    override fun getStocksFromDB(): List<Stock> =
        localRepository.getStocksFromDB()
}
package com.example.hlebushek.model.repository

import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.remote.ApiResponse
import com.example.hlebushek.model.remote.Stock
import retrofit2.Call
import javax.inject.Inject

interface MainRepository {
    fun addStockToCurrentTrade(stock: Stock)
    fun getStocksFromDB(): List<Stock>
    fun updateStock(stock: Stock)
    fun saveSettings(settings: Settings)
    fun getSettings(): Settings?
}

class MainRepositoryImpl
@Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MainRepository {
    override fun addStockToCurrentTrade(stock: Stock) =
        localRepository.addStockToCurrentTrade(stock)

    override fun getStocksFromDB(): List<Stock> =
        localRepository.getStocksFromDB()

    override fun updateStock(stock: Stock) {
        localRepository.updateStock(stock)
    }

    override fun saveSettings(settings: Settings) =
        localRepository.saveSettings(settings)

    override fun getSettings(): Settings? =
        localRepository.getSettings()
}
package com.example.hlebushek.services

import android.content.Intent
import android.os.IBinder
import com.example.hlebushek.log
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.MainRepository
import dagger.android.DaggerService
import kotlinx.coroutines.*
import javax.inject.Inject

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var stocks: List<Stock> = listOf()
    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        runBlocking {
        log("Service start work.")
            (Dispatchers.Default) {
                stocks = repository.getStocksFromDB()
            }
        }

        job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                checkLastPrices()
                delay(5000L)
            }
        }
        return START_STICKY
    }

    private fun checkLastPrices() {
        log("Service check prices.")
        stocks.forEach { stock ->
            repository.getOrderbookByFigi(stock.figi)?.payload?.lastPrice?.let { log(it) }
        }
    }

    override fun onCreate() {
        super.onCreate()
        log("Service created.")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("Service destroyed.")

        job?.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
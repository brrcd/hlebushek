package com.example.hlebushek.services

import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.hlebushek.App.Companion.TAG
import com.example.hlebushek.log
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.MainRepository
import dagger.android.DaggerService
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var stocks: List<Stock> = listOf()
    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("Service start work.")

        val mtx = Mutex()

        job = CoroutineScope(Dispatchers.IO).launch {

            stocks = repository.getStocksFromDB()

            checkPrices()
        }


        return START_STICKY
    }

    private fun checkPrices() {
        log("Service check prices.")
        log(stocks.size)
        var count = 0
        stocks.forEach { stock ->
            log(count)
            count++
            repository.getOrderbookByFigi(stock.figi)?.payload?.faceValue?.let { log(it) }
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
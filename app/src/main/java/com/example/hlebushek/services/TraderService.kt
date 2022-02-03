package com.example.hlebushek.services

import android.content.Intent
import android.os.IBinder
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.log
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.MainRepository
import dagger.android.DaggerService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var stocks: List<Stock> = listOf()
    private var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        log("Service created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = CoroutineScope(Dispatchers.IO).launch {
            stocks = repository.getStocksFromDB()
            while (true) {
                checkLastPrices()
                delay(5000L)
            }
        }
        return START_STICKY
    }

    private fun checkLastPrices() = with(CoroutineScope(Dispatchers.IO)) {
        log("Service check prices.")
        // todo stocks as flow, emit request -> onSuccess next request -> on complete Event
        stocks.asFlow()
        stocks.forEach { stock ->
        }
        EventBus.getDefault().post(Event.UpdatePrice)
    }

    override fun onDestroy() {
        log("Service destroyed.")
        job?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
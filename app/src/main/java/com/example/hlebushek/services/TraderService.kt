package com.example.hlebushek.services

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.res.Configuration
import android.os.IBinder
import android.preference.PreferenceManager
import com.example.hlebushek.log
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.MainRepository
import com.example.hlebushek.view.SettingsFragment.Companion.TAX_RATE_TAG
import dagger.android.DaggerService
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var stocks: List<Stock> = listOf()
    private var job: Job? = null

    private var taxRate = 0.0f

    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
        getTaxRateFromSharedPrefs()
        log("Service created.")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event){
        log("Service on event ${event.taxRate}")
        taxRate = event.taxRate
    }

    private fun getTaxRateFromSharedPrefs() {
        val prefs = applicationContext.getSharedPreferences(TAX_RATE_TAG, MODE_PRIVATE)
        taxRate = prefs.getFloat(TAX_RATE_TAG, 0.0f)
    }

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
        log("Service check prices. $taxRate")
        stocks.forEach { stock ->
            repository.getOrderbookByFigi(stock.figi)?.payload?.lastPrice?.let { log(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("Service destroyed.")
        EventBus.getDefault().unregister(this)
        job?.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
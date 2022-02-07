package com.example.hlebushek.services

import android.content.Intent
import android.os.IBinder
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.log
import com.example.hlebushek.model.local.LastPrice
import com.example.hlebushek.model.local.Share
import com.example.hlebushek.repository.MainRepository
import dagger.android.DaggerService
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var shares: List<Share> = listOf()
    private var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        log("Service created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = CoroutineScope(Dispatchers.IO).launch {
            shares = repository.getListOfSharesFromDB()
            while (true) {
                checkLastPrices()
                delay(10000L)
            }
        }
        return START_STICKY
    }

    private fun checkLastPrices() = with(CoroutineScope(Dispatchers.IO)) {
        val lastPrices = repository.getListOfLastPrices(shares)
        val myLastPrices = lastPrices.map {
            LastPrice(
                it.figi,
                ("${it.price.units}.${it.price.nano.toString().trimEnd('0')}").toDouble()
            )
        }
        shares.forEach { share ->
            myLastPrices.forEach {
                if (share.figi == it.figi) {
                    share.lastCheckedPrice = it.price
                }
            }
        }
        repository.updateShares(shares)
        EventBus.getDefault().post(Event.UpdatePrice)
    }

    override fun onDestroy() {
        log("Service destroyed.")
        job?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
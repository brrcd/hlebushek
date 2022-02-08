package com.example.hlebushek.services

import android.content.Intent
import android.icu.math.BigDecimal
import android.os.IBinder
import com.example.hlebushek.convertToFraction
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.log
import com.example.hlebushek.model.local.LastPrice
import com.example.hlebushek.model.local.Share
import com.example.hlebushek.repository.MainRepository
import dagger.android.DaggerService
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import ru.tinkoff.piapi.contract.v1.LastPriceOrBuilder
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class TraderService : DaggerService() {

    @Inject
    lateinit var repository: MainRepository
    private var shares: List<Share> = listOf()
    private var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        log("Service created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = CoroutineScope(Dispatchers.IO).launch {
            shares = repository.getListOfSharesFromDB()
            while (true) {
                checkLastPrices()
                delay(5000L)
            }
        }
        return START_STICKY
    }

    private fun checkLastPrices() = with(CoroutineScope(Dispatchers.IO)) {
        log("Price check.")
        val lastPrices = repository.getListOfLastPrices(shares)
        val myLastPrices = mapLastPriceOrBuilderToLastPrice(lastPrices)
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

    private fun mapLastPriceOrBuilderToLastPrice(lastPrices: List<LastPriceOrBuilder>): List<LastPrice> =
        lastPrices.map {
            LastPrice(
                it.figi,
                it.price.units.toDouble() + it.price.nano.convertToFraction(),
                it.time.seconds
            )
        }

    override fun onDestroy() {
        log("Service destroyed.")
        isRunning = false
        job?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        var isRunning = false
    }
}
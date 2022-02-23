package com.example.hlebushek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.convertToFraction
import com.example.hlebushek.log
import com.example.hlebushek.model.local.LastPrice
import com.example.hlebushek.model.local.Share
import com.example.hlebushek.repository.MainRepository
import kotlinx.coroutines.*
import ru.tinkoff.piapi.contract.v1.LastPriceOrBuilder
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var shares: List<Share> = listOf()
    private var job: Job? = null
    private val monitorScope = viewModelScope.launch(Dispatchers.IO) {
        shares = repository.getListOfSharesFromDB()
        while (isRunning) {
            checkLastPrices()
            delay(5000L)
        }
    }

    fun startMonitor() {
        isRunning = true
        monitorScope
    }

    fun stopMonitor() {
        isRunning = false
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
    }

    private fun mapLastPriceOrBuilderToLastPrice(lastPrices: List<LastPriceOrBuilder>): List<LastPrice> =
        lastPrices.map {
            LastPrice(
                it.figi,
                it.price.units.toDouble() + it.price.nano.convertToFraction(),
                it.time.seconds
            )
        }

    companion object {
        var isRunning = false
    }
}
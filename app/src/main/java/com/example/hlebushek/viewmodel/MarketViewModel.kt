package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hlebushek.AppState
import com.example.hlebushek.model.local.PayloadDTO
import com.example.hlebushek.model.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MarketViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var job: Job? = null

    fun getLiveData() = liveDataToObserve

    //TODO redo
    fun getListOfStockMarket() {
        liveDataToObserve.value = AppState.Loading
        job = CoroutineScope(Dispatchers.Default).launch {
            val data = remoteRepository.getListOfStockMarket()
            if (data != null) {
                val candles = remoteRepository.getCandleByFigi(
                    "BBG000BBV4M5",
                    getCurrentTime(),
                    getCurrentTime(),
                    "day"
                )
                liveDataToObserve.postValue(
                    AppState.Success(
                        PayloadDTO(stockList = data.payload?.instruments,
                            currentPrice = candles?.payload?.candles?.first()?.c)
                    )
                )
            }
        }
    }

    private fun getCurrentTime(): String {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale("ru", "RU"))
            .format(Date())
        val currentTime = SimpleDateFormat("hh:mm:ss", Locale("ru", "RU"))
            .format(Date())
        return (currentDate + "T" + currentTime + "+03:00")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
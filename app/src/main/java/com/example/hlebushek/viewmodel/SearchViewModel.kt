package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hlebushek.AppState
import com.example.hlebushek.model.local.PayloadDTO
import com.example.hlebushek.model.local.Portfolio
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel
@Inject constructor(private val remoteRepository: RemoteRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var job: Job? = null

    fun getLiveData() = liveDataToObserve

    fun getListOfStocksByName(stockName: String) {
        liveDataToObserve.value = AppState.Loading
        job = CoroutineScope(Dispatchers.Default).launch {
            val data = remoteRepository.getListOfStockMarket()?.payload?.instruments
                ?.filter {
                    it.name!!.lowercase().contains(stockName.lowercase())
                }

            if (data.isNullOrEmpty()) { // if filtered data is empty or null
                liveDataToObserve.postValue(
                    AppState.Error("Enter other stock name")
                )
            } else {
                liveDataToObserve.postValue(
                    AppState.Success(
                        PayloadDTO(
                            stockList = data
                        )
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
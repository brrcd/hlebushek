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

class PortfolioViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var job: Job? = null

    fun getLiveData() = liveDataToObserve

    fun getPortfolio() {
        liveDataToObserve.value = AppState.Loading
        job = CoroutineScope(Dispatchers.Default).launch {
            val data = remoteRepository.getPortfolio()
            if (data != null) {
                val positions: List<Stock>? = data.payload?.positions
                var totalValue = 0.0
                positions?.forEach {
                    totalValue += it.expectedYield?.value!!
                    totalValue += (it.averagePositionPrice?.value?.times(it.lots!!)!!)
                }
                liveDataToObserve.postValue(
                    AppState.Success(
                        PayloadDTO(
                            Portfolio(totalValue)
                        )
                    )
                )
            } else {
                //TODO response error handling
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
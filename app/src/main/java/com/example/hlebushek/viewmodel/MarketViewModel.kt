package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hlebushek.AppState
import com.example.hlebushek.model.repository.RemoteRepository
import kotlinx.coroutines.Job

class MarketViewModel(private val remoteRepository: RemoteRepository): ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var job: Job? = null

    fun getLiveData() = liveDataToObserve

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
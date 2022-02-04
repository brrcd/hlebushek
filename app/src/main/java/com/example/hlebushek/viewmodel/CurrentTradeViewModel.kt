package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.states.SearchAppState
import com.example.hlebushek.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentTradeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<SearchAppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getSharesFromDB() {
        liveDataToObserve.value = SearchAppState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfSharesFromDB()
            liveDataToObserve.postValue(
                SearchAppState.Success(
                    data
                )
            )
        }
    }
}
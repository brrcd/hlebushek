package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.hlebushek.SettingsAppState
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<SettingsAppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getSettings() {
        liveDataToObserve.value = SettingsAppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getSettings()
            if (settings == null) {
                liveDataToObserve.postValue(
                    SettingsAppState.Success(
                        Settings(
                            0.0f
                        )
                    )
                )
            } else {
                liveDataToObserve.postValue(
                    SettingsAppState.Success(
                        settings
                    )
                )
            }
        }
    }

    fun saveSettings(value: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .saveSettings(
                    Settings(value)
                )
        }
    }
}
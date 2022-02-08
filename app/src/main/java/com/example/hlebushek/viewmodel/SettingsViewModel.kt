package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.hlebushek.states.SettingsState
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<SettingsState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getSettings() {
        liveDataToObserve.value = SettingsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getSettings()
            if (settings == null) {
                liveDataToObserve.postValue(
                    SettingsState.Success(
                        Settings(
                            0.0f
                        )
                    )
                )
            } else {
                liveDataToObserve.postValue(
                    SettingsState.Success(
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
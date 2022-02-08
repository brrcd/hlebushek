package com.example.hlebushek.states

import com.example.hlebushek.model.local.Settings

sealed class SettingsState{
    data class Success(val settings: Settings): SettingsState()
    data class Error(val errorMessage: String): SettingsState()
    object Loading: SettingsState()
}

package com.example.hlebushek

import com.example.hlebushek.model.local.Settings

sealed class SettingsAppState{
    data class Success(val settings: Settings): SettingsAppState()
    data class Error(val errorMessage: String): SettingsAppState()
    object Loading: SettingsAppState()
}

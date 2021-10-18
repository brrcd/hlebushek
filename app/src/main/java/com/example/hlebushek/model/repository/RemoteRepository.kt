package com.example.hlebushek.model.repository

import com.example.hlebushek.model.ApiResponse

interface RemoteRepository {
    fun getPortfolio(): ApiResponse?
}
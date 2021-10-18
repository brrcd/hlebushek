package com.example.hlebushek.model.repository

import com.example.hlebushek.api.TraderRepo
import com.example.hlebushek.model.ApiResponse

class RemoteRepositoryImpl : RemoteRepository {
    override fun getPortfolio(): ApiResponse? {
        return TraderRepo.api.getPortfolio().execute().body()
    }
}
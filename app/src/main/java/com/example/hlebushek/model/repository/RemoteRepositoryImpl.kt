package com.example.hlebushek.model.repository

import com.example.hlebushek.api.TraderApi
import com.example.hlebushek.model.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Named

interface RemoteRepository {
}

class RemoteRepositoryImpl
@Inject  constructor(
    @Named("V2")
    private val api: TraderApi
) : RemoteRepository {
}
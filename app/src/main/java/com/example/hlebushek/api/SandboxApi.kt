package com.example.hlebushek.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface SandboxApi {
    @POST("sandbox/register")
    fun registerSandbox() : Call<Boolean>
}
package com.cs.banking.networking

import com.cs.banking.models.LoginRequest
import com.cs.banking.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest?) : Call<LoginResponse?>
}
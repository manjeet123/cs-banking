package com.cs.banking.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

object RetrofitClient {
    private const val BASE_URL = "http://213.42.43.227/cs-ce-sec-api-0.0.1/"


    private val okhttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original: Request = chain.request()
        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("requestId", UUID.randomUUID().toString())
            .header("channel", "FinMobileCS")
            .header("timeStamp", "12")
            .header("SecertKey", "FinMobileCS")
            .method(original.method(), original.body())
            .build()
        chain.proceed(request)
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServices: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

object ApiClient {
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}
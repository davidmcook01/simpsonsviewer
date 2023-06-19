package com.sample.simpsonsviewer.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://api.duckduckgo.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val demoAppService: DemoAppService by lazy {
        retrofit.create(DemoAppService::class.java)
    }
}

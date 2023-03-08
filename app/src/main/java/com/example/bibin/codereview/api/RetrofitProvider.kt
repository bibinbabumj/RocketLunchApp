package com.example.bibin.codereview.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    // Define the base URL for the API
    private const val BASE_URL = "https://api.spacexdata.com/v4/"

    /**
     * Returns a single instance of Api
     * Use Retrofit to create the API client
     */
    operator fun invoke(): Api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}
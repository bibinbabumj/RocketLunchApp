package com.example.bibin.codereview.api

import com.example.bibin.codereview.model.RocketLaunchModels
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API to retrieve data from the SpaceX API.
 */
interface Api {

    /**
     * Retrieve list of rocket launches from the SpaceX API.
     */
    @GET("launches")
    suspend fun getRocketLaunchList(): Response<List<RocketLaunchModels>>


}

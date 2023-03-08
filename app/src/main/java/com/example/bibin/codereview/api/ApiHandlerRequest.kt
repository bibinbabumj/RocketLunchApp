package com.example.bibin.codereview.api

import com.example.bibin.codereview.model.RocketLaunchModels
import retrofit2.HttpException
import retrofit2.Response

/**
Abstract class for handling API requests and returning results as [ApiResult].
 */
abstract class ApiHandlerRequest {

    suspend fun mApiHandle(execute: suspend () -> Response<List<RocketLaunchModels>>): ApiResult {
        return try {
            ApiResult.OnLoading(true)
            val response = execute.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiResult.OnSuccess(body)
            } else {
                ApiResult.OnFail(response.message(), response.code())
            }

        } catch (e: HttpException) {
            ApiResult.OnFail(e.message(), e.code())
        } catch (e: Throwable) {
            ApiResult.Exception(e.message.toString())
        }
    }
}
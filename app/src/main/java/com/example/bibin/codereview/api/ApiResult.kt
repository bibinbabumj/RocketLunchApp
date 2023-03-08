package com.example.bibin.codereview.api

import com.example.bibin.codereview.model.RocketLaunchModels

/**
 * used for representing the result of a network api operation.
 */
sealed class ApiResult {
    data class OnSuccess(val data: List<RocketLaunchModels>) : ApiResult()
    data class OnFail(var message: String, var errorCode: Int) : ApiResult()
    data class Exception(val e: String) : ApiResult()
    data class OnLoading(var isLoading: Boolean) : ApiResult()
}
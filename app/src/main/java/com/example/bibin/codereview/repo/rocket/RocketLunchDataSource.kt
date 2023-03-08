package com.example.bibin.codereview.repo.rocket

import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.model.RocketLaunchModels
import kotlinx.coroutines.flow.Flow

interface RocketLunchDataSource {
    interface RocketLunchDataSourceLocal {
        suspend fun mGetRocketListLocal(): Flow<List<RocketLaunchModels>>
        suspend fun mInsertAllRocket(data: List<RocketLaunchModels>)
    }

    interface RocketLunchDataSourceRemote {

        suspend fun mGetRocketListRemote(): ApiResult

    }
}

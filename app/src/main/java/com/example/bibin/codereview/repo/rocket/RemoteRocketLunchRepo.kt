package com.example.bibin.codereview.repo.rocket

import com.example.bibin.codereview.api.Api
import com.example.bibin.codereview.api.ApiHandlerRequest
import com.example.bibin.codereview.api.ApiResult

/**
* The RemoteRocketLunchRepo class is responsible for Api Call .
*/
class RemoteRocketLunchRepo(val api: Api) : RocketLunchDataSource.RocketLunchDataSourceRemote,
    ApiHandlerRequest(){
    /**
     * This override method gets called to retrieve the Rocket Lunch list from Remote
     */
    override suspend fun mGetRocketListRemote(): ApiResult =mApiHandle{api.getRocketLaunchList()}
}
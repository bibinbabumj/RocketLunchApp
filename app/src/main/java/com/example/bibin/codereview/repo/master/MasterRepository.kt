package com.example.bibin.codereview.repo.master

import com.example.bibin.codereview.api.Api
import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.repo.rocket.RocketLunchDataRepo
import com.example.bibin.codereview.repo.rocket.RocketLunchDataSource
import com.example.bibin.codereview.room.AppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * The MasterRepository class is responsible for acting as the data source for [RocketLunchDataRepo]
 * implements the DataSource interfaces[local and remote]
 * @param mApi The Api responsible accessing the api calls
 * @param db The AppDatabase responsible accessing the db operations
 *
 */
class MasterRepository(api: Api, db: AppDatabase) :
    RocketLunchDataSource.RocketLunchDataSourceLocal,
    RocketLunchDataSource.RocketLunchDataSourceRemote {
    private val mRocketLunchRepo: RocketLunchDataRepo

    init {
        mRocketLunchRepo = RocketLunchDataRepo(api, db)

    }

    /**
     * This override method gets called to retrieve the Rocket Lunch list from Remote
     */
    override suspend fun mGetRocketListRemote(): ApiResult =mRocketLunchRepo.mGetRocketListRemote()



    /**
     * This override method gets called to retrieve the Rocket Lunch list from Local Db
     */
    override suspend fun mGetRocketListLocal(): Flow<List<RocketLaunchModels>> =
        mRocketLunchRepo.mGetRocketListLocal()

    /**
     * This override method insert Rocket Lunch list to Local Db
     */
    override suspend fun mInsertAllRocket(data: List<RocketLaunchModels>) =
        mRocketLunchRepo.mInsertAllRocket(data)


}
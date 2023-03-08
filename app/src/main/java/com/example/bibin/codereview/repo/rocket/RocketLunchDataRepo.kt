package com.example.bibin.codereview.repo.rocket

import com.example.bibin.codereview.api.Api
import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.room.AppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * The RocketLunchDataRepo class is responsible for acting as the Local and Remote data source for [LocalRocketLunchRepo],[RemoteRocketLunchRepo]
 * implements the DataSource interfaces[local and remote]
 * @param api The Api responsible accessing the api calls
 * @param db The AppDatabase responsible accessing the db operations
 *
 */
class RocketLunchDataRepo(api: Api, db: AppDatabase) :
    RocketLunchDataSource.RocketLunchDataSourceRemote,
    RocketLunchDataSource.RocketLunchDataSourceLocal {
    private val mRemoteRocket: RemoteRocketLunchRepo
    private val mLocalRocket: LocalRocketLunchRepo

    init {
        mLocalRocket = LocalRocketLunchRepo(db)
        mRemoteRocket = RemoteRocketLunchRepo(api)
    }

    /**
     * This override method gets called to retrieve the Rocket Lunch list from Local Db
     */
    override suspend fun mGetRocketListLocal(): Flow<List<RocketLaunchModels>> =
        mLocalRocket.mGetRocketListLocal()

    /**
     * This override method insert Rocket Lunch list to Local Db
     */
    override suspend fun mInsertAllRocket(data: List<RocketLaunchModels>) =
        mLocalRocket.mInsertAllRocket(data)

    /**
     * This override method gets called to retrieve the Rocket Lunch list from Remote
     */
    override suspend fun mGetRocketListRemote(): ApiResult =mRemoteRocket.mGetRocketListRemote()


}
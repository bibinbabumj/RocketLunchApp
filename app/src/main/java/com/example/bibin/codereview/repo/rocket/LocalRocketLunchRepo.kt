package com.example.bibin.codereview.repo.rocket

import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.room.AppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * The LocalEmployeeRepo class is responsible for Local operation.
 */
class LocalRocketLunchRepo(private val db: AppDatabase) :
    RocketLunchDataSource.RocketLunchDataSourceLocal {

    /**
     * This override method gets called to retrieve the Rocket Lunch list from Local Db
     */
    override suspend fun mGetRocketListLocal(): Flow<List<RocketLaunchModels>> =
        db.dao().getAllLaunches()

    /**
     * This override method insert Rocket Lunch list to Local Db
     */
    override suspend fun mInsertAllRocket(data: List<RocketLaunchModels>) = db.dao().insertAll(data)


}
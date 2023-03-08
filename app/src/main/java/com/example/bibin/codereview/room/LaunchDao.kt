package com.example.bibin.codereview.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bibin.codereview.model.RocketLaunchModels
import kotlinx.coroutines.flow.Flow

/**
 * Dao for operating on the Room database.
 */
@Dao
interface LaunchDao {
    /**
     * Retrieve stored [RocketLaunchModels]s.
     */
    @Query("SELECT * FROM rocket_launch")
    fun getAllLaunches(): Flow<List<RocketLaunchModels>>

    /**
     * Store new [RocketLaunchModels] List.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertAll(launches: List<RocketLaunchModels>)
    @Insert
    suspend fun insert(launches: RocketLaunchModels)
}

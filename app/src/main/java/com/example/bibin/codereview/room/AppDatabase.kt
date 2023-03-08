package com.example.bibin.codereview.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bibin.codereview.model.RocketLaunchModels


/**
 * Room database for setup for application.
 */
@Database(entities = [RocketLaunchModels::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Dao for operating on the Room database.
     */
    abstract fun dao(): LaunchDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private var LOCK = Any()

        /**
         * Operator function that returns a singleton instance of the database using the Room.databaseBuilder method.
         * LOCK object is used to ensure that the database is only created once.
         */
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: mBuildDataBase(context).also {
                INSTANCE = it
            }

        }

        /**
         *  uses the Room.databaseBuilder method to create and return an instance of the database.
         */
        private fun mBuildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appDb")
                .build()

    }
}
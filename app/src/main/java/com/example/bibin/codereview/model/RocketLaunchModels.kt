package com.example.bibin.codereview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Model object to store data about a rocket launch.
 */
@Entity(tableName = "rocket_launch")
data class RocketLaunchModels(
    @SerializedName("flight_number")
    @PrimaryKey
    @ColumnInfo(name = "flightNumber")
    val flightNumber: Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date_utc")
    @SerializedName("date_utc")
    val dateUTC: String,
    @ColumnInfo(name = "details")
    @SerializedName("details")
    val details: String,
    @ColumnInfo(name = "success")
    @SerializedName("success")
    val success: Boolean
):Serializable

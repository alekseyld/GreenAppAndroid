package ru.alekseyld.greenhouseapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "green_state")
data class GreenState(
    @PrimaryKey var id: Long = 0,

    var date : String?,

    var mode : String?,

    var led: Boolean?,
    var level: Boolean?,
    var temp: Double?,
    var hydro: Boolean?,
    var fan: Boolean?,
    var solar: Int?,

    @SerializedName("pump_watering")
    @ColumnInfo(name = "pump_watering")
    var pumpWatering: Boolean?,

    @SerializedName("pump_return")
    @ColumnInfo(name = "pump_return")
    var pumpReturn: Boolean?,

    @SerializedName("finish_down")
    @ColumnInfo(name = "finish_down")
    var finishDown: Boolean?,

    @SerializedName("finish_up")
    @ColumnInfo(name = "finish_up")
    var finishUp : Boolean?,

    @SerializedName("win_drive")
    @ColumnInfo(name = "win_drive")
    var winDrive : String?,

    @SerializedName("red_led")
    @ColumnInfo(name = "red_led")
    var redLed: Boolean?
)
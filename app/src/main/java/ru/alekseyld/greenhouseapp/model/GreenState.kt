package ru.alekseyld.greenhouseapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "green_state")
data class GreenState(
    @PrimaryKey var id: Long = 0,

    var date : String,

    var led: Boolean,
    var level: Boolean,
    var temp: Double,
    var hydro: Boolean,
    var fan: Boolean,
    var solar: Int,

    @ColumnInfo(name = "pump_watering")
    var pumpWatering: Boolean,

    @ColumnInfo(name = "pump_return")
    var pumpReturn: Boolean,

    @ColumnInfo(name = "finish_down")
    var finishDown: Boolean,

    @ColumnInfo(name = "finish_up")
    var finishUp : Boolean,

    @ColumnInfo(name = "win_drive")
    var winDrive : String,

    @ColumnInfo(name = "red_led")
    var redLed: Boolean
)
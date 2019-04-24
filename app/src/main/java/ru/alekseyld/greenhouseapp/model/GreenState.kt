package ru.alekseyld.greenhouseapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "green_state")
open class GreenState(

    @Ignore var error: String? = null,

    @PrimaryKey var id: Long = 0,

    var date : String? = null,

    var mode : String? = null,

    var led: Boolean? = null,
    var level: Boolean? = null,
    var temp: Double? = null,
    var hydro: Boolean? = null,
    var fan: Boolean? = null,
    var solar: Int? = null,

    @SerializedName("pump_watering")
    @ColumnInfo(name = "pump_watering")
    var pumpWatering: Boolean? = null,

    @SerializedName("pump_return")
    @ColumnInfo(name = "pump_return")
    var pumpReturn: Boolean? = null,

    @SerializedName("finish_down")
    @ColumnInfo(name = "finish_down")
    var finishDown: Boolean? = null,

    @SerializedName("finish_up")
    @ColumnInfo(name = "finish_up")
    var finishUp : Boolean? = null,

    @SerializedName("win_drive")
    @ColumnInfo(name = "win_drive")
    var winDrive : Boolean? = null,

    @SerializedName("red_led")
    @ColumnInfo(name = "red_led")
    var redLed: Boolean? = null
)
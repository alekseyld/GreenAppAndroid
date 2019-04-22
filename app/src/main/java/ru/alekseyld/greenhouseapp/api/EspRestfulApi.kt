package ru.alekseyld.greenhouseapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alekseyld.greenhouseapp.model.GreenState


interface EspRestfulApi {

    @GET("/setmode")
    fun setMode(@Query("mode") mode: String): Single<GreenState>

    @GET("/getstate")
    fun getState(@Query("node") node: String): Single<GreenState>

    @GET("/setstate")
    fun setState(@Query("node") node: String): Single<GreenState>

}
package ru.alekseyld.greenhouseapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.model.PieceOfState


interface EspRestfulApi {

    @GET("/setmode")
    fun setMode(@Query("mode") mode: String): Single<PieceOfState>

    @GET("/getstate")
    fun getState(@Query("node") node: String): Single<PieceOfState>

    @GET("/getstate?node=all")
    fun getAllStates(): Single<GreenState>

    @GET("/setstate")
    fun setState(@Query("node") node: String,
                 @Query("state") state: String): Single<PieceOfState>

}
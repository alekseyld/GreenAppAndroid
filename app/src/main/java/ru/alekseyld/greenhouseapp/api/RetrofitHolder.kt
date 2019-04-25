package ru.alekseyld.greenhouseapp.api

import com.google.gson.GsonBuilder
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.alekseyld.greenhouseapp.typeadapter.BooleanTypeAdapter

class RetrofitHolder {

    val retrofit = BehaviorSubject.create<Retrofit>()

    fun recreateRetrofit(url: String) {

        val gson = GsonBuilder()
            .registerTypeAdapter(Boolean::class.javaObjectType, BooleanTypeAdapter())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter())
            .setLenient()
            .create()

        retrofit.onNext(Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build())
    }

}
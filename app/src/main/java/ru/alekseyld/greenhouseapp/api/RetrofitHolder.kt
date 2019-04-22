package ru.alekseyld.greenhouseapp.api

import android.arch.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHolder {

    val retrofit: MutableLiveData<Retrofit> = MutableLiveData()

    fun recreateRetrofit(url: String) {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit.value = Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}
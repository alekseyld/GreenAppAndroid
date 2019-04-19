package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.local.RoomGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.network.EspGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.room.GreenAppDatabase
import ru.alekseyld.greenhouseapp.repository.room.GreenStateDao
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    private val BASE_URL = "http://192.168.88.234"
    private val PEFERENCE_NAME = "green_app"

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    @Named("local")
    fun provideRoomGreenStateRepository(greenStateDao: GreenStateDao): IGreenStateRepository = RoomGreenStateRepository(greenStateDao)

    @Singleton
    @Provides
    @Named("network")
    fun provideEspGreenStateRepository(): IGreenStateRepository = EspGreenStateRepository()

    @Singleton
    @Provides
    fun provideGreenStateDao(db: GreenAppDatabase): GreenStateDao = db.greenStateDao()

    @Singleton
    @Provides
    fun provideDataBase(context: Context): GreenAppDatabase =
        Room.databaseBuilder(context, GreenAppDatabase::class.java, "greendb")
            .build()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PEFERENCE_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideRetrofit(sharedPreferences: SharedPreferences): Retrofit {

        val url = sharedPreferences.getString("esp_url", BASE_URL)!!

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
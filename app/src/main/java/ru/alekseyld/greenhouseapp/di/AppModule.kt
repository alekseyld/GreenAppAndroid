package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import ru.alekseyld.greenhouseapp.repository.IEspRepository
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.local.RoomGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.network.EspGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.room.GreenAppDatabase
import ru.alekseyld.greenhouseapp.repository.room.GreenStateDao
import ru.alekseyld.greenhouseapp.service.GreenStateService
import ru.alekseyld.greenhouseapp.service.IGreenStateService
import ru.alekseyld.greenhouseapp.ui.settings.SettingsFragment
import javax.inject.Singleton



@Module
class AppModule {

    companion object {
        //var BASE_URL = "http://192.168.88.239"
        var BASE_URL = "http://192.168.4.1"
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideRoomGreenStateRepository(greenStateDao: GreenStateDao): IGreenStateRepository
            = RoomGreenStateRepository(greenStateDao)

    @Singleton
    @Provides
    fun provideEspGreenStateRepository(holder: RetrofitHolder): IEspRepository
            = EspGreenStateRepository(holder)

    @Singleton
    @Provides
    fun provideGreenStateService(espRepository: IEspRepository, greenStateRepository: IGreenStateRepository): IGreenStateService
            = GreenStateService(espRepository, greenStateRepository)

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
    fun provideSharedPreferences(context: Context): SharedPreferences {
        val sharedPreferences
                = context.getSharedPreferences(SettingsFragment.PEFERENCE_NAME, Context.MODE_PRIVATE)

        if (!sharedPreferences.contains(SettingsFragment.ESP_IP)) {
            sharedPreferences.edit()
                .putString(SettingsFragment.ESP_IP, BASE_URL)
                .apply()
        }

        return sharedPreferences
    }

    @Singleton
    @Provides
    fun provideRetrofit(sharedPreferences: SharedPreferences): RetrofitHolder {

        val url = sharedPreferences.getString(SettingsFragment.ESP_IP, BASE_URL)!!

        val retrofitHolder = RetrofitHolder()

        retrofitHolder.recreateRetrofit(url)

        return retrofitHolder
    }
}
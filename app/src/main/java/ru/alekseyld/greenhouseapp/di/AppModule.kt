package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.local.RoomGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.network.EspGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.room.GreenAppDatabase
import ru.alekseyld.greenhouseapp.repository.room.GreenStateDao
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

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
}
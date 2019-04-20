package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.room.GreenAppDatabase
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    @Named("local")
    fun getRoomGreenStateRepository(): IGreenStateRepository

    @Named("network")
    fun getEspGreenStateRepository(): IGreenStateRepository

    fun getDatabase(): GreenAppDatabase

    fun getViewModelFactory(): ViewModelProvider.Factory
}
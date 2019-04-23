package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import ru.alekseyld.greenhouseapp.repository.IEspRepository
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
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

    fun getEspGreenStateRepository(): IEspRepository

    fun getViewModelFactory(): ViewModelProvider.Factory

    fun getRetrofitHolder(): RetrofitHolder
}
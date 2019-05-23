package ru.alekseyld.greenhouseapp.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import ru.alekseyld.greenhouseapp.service.IGreenStateService
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

    fun getSharedPreference(): SharedPreferences

    fun getGreenStateService(): IGreenStateService

    fun getViewModelFactory(): ViewModelProvider.Factory

    fun getRetrofitHolder(): RetrofitHolder
}
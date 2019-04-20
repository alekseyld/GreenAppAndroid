package ru.alekseyld.greenhouseapp.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.alekseyld.greenhouseapp.DaggerViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}
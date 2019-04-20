package ru.alekseyld.greenhouseapp.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alekseyld.greenhouseapp.viewmodel.ControlViewModel


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ControlViewModel::class)
    abstract fun bindMyViewModel(viewModel: ControlViewModel): ViewModel
}
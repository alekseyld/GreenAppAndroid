package ru.alekseyld.greenhouseapp.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alekseyld.greenhouseapp.viewmodel.ControlViewModel
import ru.alekseyld.greenhouseapp.viewmodel.StatisticsViewModel


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ControlViewModel::class)
    abstract fun bindControlViewModel(viewModel: ControlViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindStatisticsViewModel(viewModel: StatisticsViewModel): ViewModel
}
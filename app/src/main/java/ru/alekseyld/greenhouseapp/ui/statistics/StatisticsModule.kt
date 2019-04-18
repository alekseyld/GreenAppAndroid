package ru.alekseyld.greenhouseapp.ui.control

import dagger.Module
import dagger.Provides
import ru.alekseyld.greenhouseapp.di.PerScreen

@Module
class StatisticsModule {

    @PerScreen
    @Provides
    fun providePresenter() = StatisticsPresenter()

}
package ru.alekseyld.greenhouseapp.ui.control

import dagger.Component
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.PerScreen
import ru.alekseyld.greenhouseapp.ui.statistics.StatisticsFragment

@PerScreen
@Component(modules = [(StatisticsModule::class)],
    dependencies = [(AppComponent::class)])
interface StatisticsComponent {
    fun inject(fragment: StatisticsFragment)
}
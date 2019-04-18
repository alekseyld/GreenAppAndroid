package ru.alekseyld.greenhouseapp.ui.control

import dagger.Component
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.PerScreen
import ru.alekseyld.greenhouseapp.ui.settings.SettingsFragment

@PerScreen
@Component(modules = [(SettingsModule::class)],
    dependencies = [(AppComponent::class)])
interface SettingsComponent {
    fun inject(fragment: SettingsFragment)
}
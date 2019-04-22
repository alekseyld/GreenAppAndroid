package ru.alekseyld.greenhouseapp.ui.settings

import dagger.Component
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.PerScreen

@PerScreen
@Component(dependencies = [(AppComponent::class)])
interface SettingsComponent {
    fun inject(fragment: SettingsFragment)
}
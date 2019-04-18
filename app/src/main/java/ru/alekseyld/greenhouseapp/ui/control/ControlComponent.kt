package ru.alekseyld.greenhouseapp.ui.control

import dagger.Component
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.PerScreen

@PerScreen
@Component(modules = [(ControlModule::class)],
    dependencies = [(AppComponent::class)])
interface ControlComponent {
    fun inject(fragment: ControlFragment)
}
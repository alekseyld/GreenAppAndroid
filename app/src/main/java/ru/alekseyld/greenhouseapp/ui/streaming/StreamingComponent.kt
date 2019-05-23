package ru.alekseyld.greenhouseapp.ui.streaming

import dagger.Component
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.PerScreen

@PerScreen
@Component(modules = [(StreamingModule::class)],
    dependencies = [(AppComponent::class)])
interface StreamingComponent {
    fun inject(fragment: StreamingFragment)
}
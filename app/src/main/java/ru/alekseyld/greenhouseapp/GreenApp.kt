package ru.alekseyld.greenhouseapp

import android.app.Application
import ru.alekseyld.greenhouseapp.di.AppComponent
import ru.alekseyld.greenhouseapp.di.DaggerAppComponent

class GreenApp : Application() {

    companion object {
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}
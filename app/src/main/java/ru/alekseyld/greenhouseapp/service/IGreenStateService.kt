package ru.alekseyld.greenhouseapp.service

import android.arch.lifecycle.MutableLiveData
import ru.alekseyld.greenhouseapp.model.EspRequest
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.observable.ObservableObject

interface IGreenStateService {

    val greenState: MutableLiveData<GreenState>

    val errorMessage: MutableLiveData<String>

    val isLoading: ObservableObject<Boolean>

    fun addToSchedule(espRequest: EspRequest)

}
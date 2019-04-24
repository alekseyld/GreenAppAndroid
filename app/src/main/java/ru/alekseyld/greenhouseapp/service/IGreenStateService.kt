package ru.alekseyld.greenhouseapp.service

import android.arch.lifecycle.MutableLiveData
import ru.alekseyld.greenhouseapp.model.EspRequest
import ru.alekseyld.greenhouseapp.model.GreenState

interface IGreenStateService {

    val greenState: MutableLiveData<GreenState>

    val errorMessage: MutableLiveData<String>

    fun addToSchedule(espRequest: EspRequest)

}
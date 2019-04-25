package ru.alekseyld.greenhouseapp.service

import io.reactivex.subjects.Subject
import ru.alekseyld.greenhouseapp.model.EspRequest
import ru.alekseyld.greenhouseapp.model.GreenState

interface IGreenStateService {

    val greenState: Subject<GreenState>

    val errorMessage: Subject<String>

    val isLoading: Subject<Boolean>

    fun addToSchedule(espRequest: EspRequest)

}
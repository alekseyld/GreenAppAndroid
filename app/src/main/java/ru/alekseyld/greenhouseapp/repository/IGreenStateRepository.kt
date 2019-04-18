package ru.alekseyld.greenhouseapp.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyld.greenhouseapp.model.GreenState

interface IGreenStateRepository {
    fun getAllGreenStates(): Single<List<GreenState>>
    fun getGreenState(): Single<GreenState>
    fun saveGreenState(greenState: GreenState): Completable
}
package ru.alekseyld.greenhouseapp.repository.network

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository

class EspGreenStateRepository : IGreenStateRepository {

    override fun getAllGreenStates(): Single<List<GreenState>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGreenState(): Single<GreenState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveGreenState(greenState: GreenState): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
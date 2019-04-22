package ru.alekseyld.greenhouseapp.repository.network

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyld.greenhouseapp.api.EspRestfulApi
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository

class EspGreenStateRepository(retrofitHolder: RetrofitHolder) : IGreenStateRepository {

    private var api: EspRestfulApi

    init {

        api = retrofitHolder.retrofit.value!!.create(EspRestfulApi::class.java)

        retrofitHolder.retrofit.observeForever {

            api = it!!.create(EspRestfulApi::class.java)
        }
    }

    override fun getAllGreenStates(): Single<List<GreenState>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGreenState(): Single<GreenState> {
        return api.getState("all")
    }

    override fun saveGreenState(greenState: GreenState): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
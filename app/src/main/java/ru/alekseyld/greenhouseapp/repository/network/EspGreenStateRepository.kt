package ru.alekseyld.greenhouseapp.repository.network

import io.reactivex.Single
import ru.alekseyld.greenhouseapp.api.EspRestfulApi
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.model.PieceOfState
import ru.alekseyld.greenhouseapp.repository.IEspRepository

class EspGreenStateRepository(retrofitHolder: RetrofitHolder) : IEspRepository {

    private var api: EspRestfulApi

    init {

        api = retrofitHolder.retrofit.value!!.create(EspRestfulApi::class.java)

        retrofitHolder.retrofit.observeForever {

            api = it!!.create(EspRestfulApi::class.java)
        }
    }

    override fun getAllStates(): Single<GreenState>
        = api.getAllStates()


    override fun getState(node: IEspRepository.Node): Single<PieceOfState>
        = api.getState(node.string)


    override fun setState(node: IEspRepository.Node, state: IEspRepository.State): Single<PieceOfState>
        = api.setState(node.string, state.string)


    override fun setMode(mode: IEspRepository.State): Single<PieceOfState>
        = api.setMode(mode.string)
}
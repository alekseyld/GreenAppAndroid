package ru.alekseyld.greenhouseapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.model.updateState
import ru.alekseyld.greenhouseapp.repository.IEspRepository
import ru.alekseyld.greenhouseapp.viewmodel.base.BaseViewModel
import javax.inject.Inject

class ControlViewModel @Inject constructor(
    private val networkRepository: IEspRepository
) : BaseViewModel() {

    val greenState: MutableLiveData<GreenState> = MutableLiveData()

    init {
        greenState.value = GreenState()
    }

    fun updateAll() {
        disposable.value =
                networkRepository.getAllStates()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            greenState.value = it
                        },
                        this::onError
                    )
    }

    fun setState(node: IEspRepository.Node, state: IEspRepository.State) {
        disposable.value =
                networkRepository.setState(node, state)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            greenState.value = it.updateState(greenState.value!!)
                        },
                        this::onError
                    )
    }

    fun setMode(state: IEspRepository.State) {
        disposable.value =
                networkRepository.setMode(state)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            greenState.value = it.updateState(greenState.value!!)
                        },
                        this::onError
                    )
    }
}
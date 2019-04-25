package ru.alekseyld.greenhouseapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import ru.alekseyld.greenhouseapp.model.EspRequest
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IEspRepository
import ru.alekseyld.greenhouseapp.service.IGreenStateService
import ru.alekseyld.greenhouseapp.viewmodel.base.BaseViewModel
import javax.inject.Inject

class ControlViewModel @Inject constructor(
    private val service: IGreenStateService
) : BaseViewModel() {

    val greenState = MutableLiveData<GreenState>()
    val loading = service.isLoading

    init {
        disposable.value = service.greenState.subscribe {
            greenState.value = it
        }

        disposable.value = service.errorMessage.subscribe {
            errorMessage.value = it
        }
    }

    private fun addRequest(type: EspRequest.Type) = addRequest(type, listOf())

    private fun addRequest(type: EspRequest.Type, params: List<Any>) {
        service.addToSchedule(
            EspRequest(
                requestType = type,
                param = params,
                disposableHandler = this::addDisposable
            )
        )
    }

    fun updateAll() = addRequest(EspRequest.Type.GetAllStates)

    fun setState(node: IEspRepository.Node, state: IEspRepository.State) {

        if ((greenState.value?.mode ?: "auto")
            == IEspRepository.State.Auto.string) {

            errorMessage.value = "В автоматическом режиме запрещено управление"

            return
        }

        addRequest(EspRequest.Type.SetState, listOf(node, state))
    }

    fun setMode(state: IEspRepository.State)
            = addRequest(EspRequest.Type.SetMode, listOf(state))
}
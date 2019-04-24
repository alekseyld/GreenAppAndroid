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

    val greenState: MutableLiveData<GreenState> = MutableLiveData()

    init {
        greenState.value = service.greenState.value

        service.greenState.observeForever {
            greenState.value = it
        }

        service.errorMessage.observeForever {
            errorMessage.value = it
        }
    }

    fun updateAll() {
        service.addToSchedule(
            EspRequest(
                EspRequest.Type.GetAllStates
            ) {
                disposable.value = it
            }
        )
    }

    fun setState(node: IEspRepository.Node, state: IEspRepository.State) {
        service.addToSchedule(
            EspRequest(
                EspRequest.Type.SetState,
                listOf(node, state)
            ) {
                disposable.value = it
            }
        )
    }

    fun setMode(state: IEspRepository.State) {
        service.addToSchedule(
            EspRequest(
                EspRequest.Type.SetMode,
                listOf(state)
            ) {
                disposable.value = it
            }
        )
    }
}
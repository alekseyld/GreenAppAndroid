package ru.alekseyld.greenhouseapp.service

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alekseyld.greenhouseapp.model.EspRequest
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.model.PieceOfState
import ru.alekseyld.greenhouseapp.model.updateState
import ru.alekseyld.greenhouseapp.observable.ObservableObject
import ru.alekseyld.greenhouseapp.repository.IEspRepository
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import java.util.*
import kotlin.concurrent.schedule

class GreenStateService(
    private val networkRepository: IEspRepository,
    private val localRepository: IGreenStateRepository
) : IGreenStateService {

    private val queue: Queue<EspRequest>

    private val state = MutableLiveData<GreenState>()
    private val error = MutableLiveData<String>()
    private val loading = ObservableObject<Boolean>()

    private var currentRequest: EspRequest? = null

    init {
        state.value = GreenState()
        loading.notifyObserver(false)

        queue = ArrayDeque()

        Timer(
            "GreenStateScheduler",
            true
        ).schedule(0L, 3500L) {
            processScheduler()
        }
    }

    override val greenState: MutableLiveData<GreenState>
        get() = state

    override val errorMessage: MutableLiveData<String>
        get() = error

    override val isLoading: ObservableObject<Boolean>
        get() = loading

    override fun addToSchedule(espRequest: EspRequest) {
        val last = queue.peek()

        if (last?.equals(espRequest) != true) {
            loading.notifyObserver(true)

            queue.offer(espRequest)
        }
    }

    private fun clearRequest() {
        currentRequest?.let {
            it.disposableHandler(it.disposable!!)
        }
        currentRequest = null
        loading.notifyObserver(queue.size > 0)
    }

    private fun onNext(greenState: GreenState) {
        state.value = if (greenState is PieceOfState) {
            greenState.updateState(state.value!!)

        } else greenState

        state.value?.error?.let { error -> errorMessage.value = error }

        clearRequest()
    }

    private fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        throwable.message?.let {
            errorMessage.value = it
        }

        clearRequest()
    }

    private fun processScheduler() {

        val request = queue.poll()

        request?.let {

            currentRequest = it

            val single = when (it.requestType) {
                EspRequest.Type.GetAllStates ->
                    networkRepository.getAllStates()

                EspRequest.Type.GetState ->
                    networkRepository
                        .getState(request.param[0] as IEspRepository.Node)

                EspRequest.Type.SetMode ->
                    networkRepository
                        .setMode(request.param[0] as IEspRepository.State)

                EspRequest.Type.SetState ->
                    networkRepository
                        .setState(request.param[0] as IEspRepository.Node,
                                  request.param[1] as IEspRepository.State)
            }

            val disposable = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::onNext,
                    this::onError
                )

            it.disposable = disposable
        }
    }
}
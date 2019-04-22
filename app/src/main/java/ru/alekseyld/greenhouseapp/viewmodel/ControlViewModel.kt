package ru.alekseyld.greenhouseapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import javax.inject.Inject
import javax.inject.Named

class ControlViewModel @Inject constructor(
    @Named("network") val networkRepository : IGreenStateRepository
) : ViewModel() {

    var greenState: MutableLiveData<GreenState> = MutableLiveData()

    fun updateAll() : Disposable {
        return networkRepository.getGreenState()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    greenState.value = it
                },
                {
                    it.printStackTrace()
                }
            )
    }

}
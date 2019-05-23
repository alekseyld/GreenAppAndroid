package ru.alekseyld.greenhouseapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.viewmodel.base.BaseViewModel
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val localRepository: IGreenStateRepository
) : BaseViewModel() {

    val greenStates = MutableLiveData<List<GreenState>>()

    fun updateGreenStates() {
        disposable.value =
                localRepository.getAllGreenStates()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            greenStates.value = it
                        },
                        {
                            errorMessage.value = it.message
                        }
                    )
    }

}
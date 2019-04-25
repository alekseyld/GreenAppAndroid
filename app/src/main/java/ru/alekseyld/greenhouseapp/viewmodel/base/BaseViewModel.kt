package ru.alekseyld.greenhouseapp.viewmodel.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val disposable: MutableLiveData<Disposable> = MutableLiveData()

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        throwable.message?.let {
            errorMessage.value = it
        }
    }

    fun addDisposable(d: Disposable) {
        disposable.value = d
    }

}
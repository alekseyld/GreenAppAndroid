package ru.alekseyld.greenhouseapp.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<TView> : MvpPresenter<TView> {

    protected val tag: String = this.javaClass.simpleName

    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected var viewMVP: TView? = null
        private set

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {
        this.viewMVP = null
        disposables.dispose()
    }

    override fun attachView(view: TView) {
        this.viewMVP = view
    }
}
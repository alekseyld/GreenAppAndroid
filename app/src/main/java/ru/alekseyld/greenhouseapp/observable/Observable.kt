package ru.alekseyld.greenhouseapp.observable

/*
 * Created by Alekseyld on 13.4.2019.
 */

interface Observable<T> {

    fun addObserver(observer: Observer<T>)
    fun removeObserver(observer: Observer<T>)
    fun notifyObserver(obj: T)

}

typealias Observer<T> = (T) -> Unit

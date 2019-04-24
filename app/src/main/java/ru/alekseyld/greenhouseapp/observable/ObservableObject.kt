package ru.alekseyld.greenhouseapp.observable

/*
 * Created by Alekseyld on 13.4.2019.
 */

import java.util.*

class ObservableObject<T> : Observable<T> {
    private val observers = Vector<Observer<T>>()
    private var lastValue : T? = null

    override fun addObserver(observer: Observer<T>) {
        if (!observers.contains(observer)) {
            observers.addElement(observer)
        }
    }

    override fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    override fun notifyObserver(obj: T) {

        if (lastValue?.equals(obj) == true) {
            return
        }

        for (observer in observers) {
            observer(obj)
        }

        lastValue = obj
    }
}

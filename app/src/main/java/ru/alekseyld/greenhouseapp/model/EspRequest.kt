package ru.alekseyld.greenhouseapp.model

import io.reactivex.disposables.Disposable

data class EspRequest(
    val requestType: Type,
    val param: List<Any>,
    var disposable: Disposable? = null,
    val disposableHandler: (Disposable) -> Unit
) {

    enum class Type {
        GetAllStates, GetState, SetState, SetMode
    }

    override fun equals(other: Any?): Boolean {
        if (other is EspRequest
            && other.requestType == requestType) {

            if (param.isNotEmpty()
                && other.param.isNotEmpty()) {

                val b = param[0] == other.param[0]

                val b2 = if (param.size > 1
                    && other.param.size > 1) {

                    param[1] == other.param[1]
                } else true

                return b && b2
            }

            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var result = requestType.hashCode()
        result = 31 * result + param.hashCode()
        result = 31 * result + (disposable?.hashCode() ?: 0)
        result = 31 * result + disposableHandler.hashCode()
        return result
    }
}
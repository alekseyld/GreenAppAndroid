package ru.alekseyld.greenhouseapp.repository

import io.reactivex.Single
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.model.PieceOfState

interface IEspRepository {

    enum class Node(val string: String) {
        Mode("mode"),
        Led("led"),
        Temp("temp"),
        Hydro("hydro"),
        Fan("fan"),
        Solar("solar"),
        PumpWatering("pump_watering"),
        PumpReturn("pump_return"),
        FinishDown("finish_down"),
        FinishUp("finish_up"),
        WinDrive("win_drive"),
        RedLed("red_led")
    }

    enum class State (val string: String) {
        Auto("auto"),
        Manual("manual"),
        On("1"),
        Off("0"),
        Open("open"),
        Close("close")
    }

    fun getAllStates() : Single<GreenState>
    fun getState(node: Node) : Single<PieceOfState>
    fun setState(node: Node, state: State) : Single<PieceOfState>
    fun setMode(mode: State) : Single<PieceOfState>

}
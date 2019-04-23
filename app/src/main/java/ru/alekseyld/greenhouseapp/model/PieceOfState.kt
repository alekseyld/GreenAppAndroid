package ru.alekseyld.greenhouseapp.model

class PieceOfState : GreenState()

fun PieceOfState.updateState(greenState: GreenState) : GreenState {

    this.mode?.let { greenState.mode = it }
    this.temp?.let { greenState.temp = it }
    this.hydro?.let { greenState.hydro = it }
    this.solar?.let { greenState.solar = it }
    this.level?.let { greenState.level = it }
    this.led?.let { greenState.led = it }
    this.pumpReturn?.let { greenState.pumpReturn = it }
    this.pumpWatering?.let { greenState.pumpWatering = it }
    this.winDrive?.let { greenState.winDrive = it }
    this.fan?.let { greenState.fan = it }
    this.redLed?.let { greenState.redLed = it }
    this.finishUp?.let { greenState.finishUp = it }
    this.finishDown?.let { greenState.finishDown = it }

    return greenState
}
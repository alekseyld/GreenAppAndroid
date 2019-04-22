package ru.alekseyld.greenhouseapp.ui.control

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_control.*
import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentControlBinding
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.ui.widget.NodeView
import ru.alekseyld.greenhouseapp.viewmodel.ControlViewModel

class ControlFragment : BaseBindingFragment<ControlViewModel, FragmentControlBinding>() {

    override fun bindVariable() {
        binding.viewModel = viewModel


        viewModel.greenState.observe(this, Observer {
            updateView(it!!)
        })
    }

    private fun updateView(greenState: GreenState) {

        node_temp.setStringParam(greenState.temp, " °C")
        node_hydro.setTurnParam(greenState.hydro)
        node_solar.setStringParam(greenState.solar, " %")
        node_level.setTurnParam(greenState.level)
        node_led.setTurnParam(greenState.led)
        node_return.setTurnParam(greenState.pumpReturn)
        node_watering.setTurnParam(greenState.pumpWatering)
        node_servo.setStringParam(greenState.winDrive)
        node_fan.setTurnParam(greenState.fan)
        node_red.setTurnParam(greenState.redLed)
        node_finish_up.setTurnParam(greenState.finishUp)
        node_finish_down.setTurnParam(greenState.finishDown)
    }

    private fun NodeView.setStringParam(value: Any?, postfix: String = "") {
        value?.toString()?.let {
            this.valueText = "$it$postfix"
        }
    }

    private fun NodeView.setTurnParam(value: Boolean?) {
        value?.let {
            this.isTurn = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_refresh, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_refresh ->{
                disposable.add(viewModel.updateAll())
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.fragment_control

    override fun injectDependencies() {
        DaggerControlComponent.builder()
            .appComponent(GreenApp.component)
            .controlModule(ControlModule())
            .build()
            .inject(this)
    }

    override fun onBackKeyPressed() {
    }
}
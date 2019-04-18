package ru.alekseyld.greenhouseapp.ui.control

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseFragment

class ControlFragment : BaseFragment<ControlPresenter, ControlContract.View>(), ControlContract.View {

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
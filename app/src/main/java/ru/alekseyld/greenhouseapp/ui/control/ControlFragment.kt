package ru.alekseyld.greenhouseapp.ui.control

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentControlBinding
import ru.alekseyld.greenhouseapp.viewmodel.ControlViewModel

class ControlFragment : BaseBindingFragment<ControlViewModel, FragmentControlBinding>() {

    override fun bindVariable() {
        binding.viewModel = viewModel
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
package ru.alekseyld.greenhouseapp.ui.settings

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentSettingsBinding
import ru.alekseyld.greenhouseapp.ui.control.DaggerSettingsComponent
import ru.alekseyld.greenhouseapp.ui.control.SettingsModule
import ru.alekseyld.greenhouseapp.viewmodel.SettingsViewModel

class SettingsFragment : BaseBindingFragment<SettingsViewModel, FragmentSettingsBinding>() {

    override fun bindVariable() {
        binding.viewModel = viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun injectDependencies() {
        DaggerSettingsComponent.builder()
            .appComponent(GreenApp.component)
            .settingsModule(SettingsModule())
            .build()
            .inject(this)
    }

    override fun onBackKeyPressed() {
    }
}
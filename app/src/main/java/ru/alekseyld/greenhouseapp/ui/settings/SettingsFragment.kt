package ru.alekseyld.greenhouseapp.ui.settings

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseFragment
import ru.alekseyld.greenhouseapp.ui.control.DaggerSettingsComponent
import ru.alekseyld.greenhouseapp.ui.control.SettingsContract
import ru.alekseyld.greenhouseapp.ui.control.SettingsModule
import ru.alekseyld.greenhouseapp.ui.control.SettingsPresenter

class SettingsFragment : BaseFragment<SettingsPresenter, SettingsContract.View>(), SettingsContract.View {

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
package ru.alekseyld.greenhouseapp.ui.control

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseFragment
import ru.alekseyld.greenhouseapp.viewmodel.ControlViewModel
import javax.inject.Inject

class ControlFragment : BaseFragment<ControlPresenter, ControlContract.View>(), ControlContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var demoViewModel: ControlViewModel

    override fun getLayoutId(): Int = R.layout.fragment_control

    override fun injectDependencies() {
        DaggerControlComponent.builder()
            .appComponent(GreenApp.component)
            .controlModule(ControlModule())
            .build()
            .inject(this)

        demoViewModel = ViewModelProviders.of(this, viewModelFactory)[ControlViewModel::class.java]
    }

    override fun onBackKeyPressed() {
    }
}
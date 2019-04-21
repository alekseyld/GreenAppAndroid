package ru.alekseyld.greenhouseapp.ui.statistics

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentStatisticsBinding
import ru.alekseyld.greenhouseapp.ui.control.DaggerStatisticsComponent
import ru.alekseyld.greenhouseapp.ui.control.StatisticsModule
import ru.alekseyld.greenhouseapp.viewmodel.StatisticsViewModel

class StatisticsFragment : BaseBindingFragment<StatisticsViewModel, FragmentStatisticsBinding>() {

    override fun bindVariable() {
        binding.viewModel = viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_statistics

    override fun injectDependencies() {
        DaggerStatisticsComponent.builder()
            .appComponent(GreenApp.component)
            .statisticsModule(StatisticsModule())
            .build()
            .inject(this)
    }

    override fun onBackKeyPressed() {
    }
}
package ru.alekseyld.greenhouseapp.ui.statistics

import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseFragment
import ru.alekseyld.greenhouseapp.ui.control.DaggerStatisticsComponent
import ru.alekseyld.greenhouseapp.ui.control.StatisticsContract
import ru.alekseyld.greenhouseapp.ui.control.StatisticsModule
import ru.alekseyld.greenhouseapp.ui.control.StatisticsPresenter

class StatisticsFragment : BaseFragment<StatisticsPresenter, StatisticsContract.View>(), StatisticsContract.View {

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
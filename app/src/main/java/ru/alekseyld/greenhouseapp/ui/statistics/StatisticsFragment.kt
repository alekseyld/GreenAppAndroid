package ru.alekseyld.greenhouseapp.ui.statistics

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.android.synthetic.main.fragment_statistics.*
import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentStatisticsBinding
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.ui.control.DaggerStatisticsComponent
import ru.alekseyld.greenhouseapp.ui.control.StatisticsModule
import ru.alekseyld.greenhouseapp.viewmodel.StatisticsViewModel


class StatisticsFragment : BaseBindingFragment<StatisticsViewModel, FragmentStatisticsBinding>() {

    private var blueColor : Int = 0
    private var yellowColor : Int = 0
    private val greenColor = Color.rgb(137, 230, 81)

    private fun Boolean.toFloat() = if (this) 1f else 0f

    override fun bindVariable() {
        binding.viewModel = viewModel

        viewModel.greenStates.observe(this, Observer { it1 ->
            it1?.let {

                val tempData = it.getLineChartDataFor { greenState -> greenState.temp!!.toFloat() }
                setDataToChart(tempLineChart, tempData, greenColor)

                val lightData = it.getLineChartDataFor { greenState -> greenState.solar!!.toFloat() }
                setDataToChart(lightLineChart, lightData, yellowColor)

                val hydro = it.getLineChartDataFor(false) { greenState -> greenState.hydro!!.toFloat() }
                setDataToChart(hydroLineChart, hydro, blueColor)
            }
        })
    }

    private fun List<GreenState>.getLineChartDataFor(need: Boolean = true, value: (GreenState) -> Float) : LineDataSet{

        val values = ArrayList<Entry>()

        for (i in 0 until this.size) {

            val `val` = value(this[i])
            values.add(Entry(i.toFloat(), `val`))
        }

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.lineWidth = 1.75f
        //set1.circleRadius = 5f
        // set1.circleHoleRadius = 2.5f
        set1.color = Color.WHITE
        set1.setCircleColor(Color.WHITE)
        set1.highLightColor = Color.WHITE
        set1.setDrawValues(false)

        set1.setDrawFilled(true)
        set1.fillAlpha = 255
        set1.setDrawCircles(false)

        if (need) {
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        return set1
    }

    private fun setDataToChart(chart: LineChart,
                               data: LineDataSet,
                               color: Int) {
        data.fillColor = color

        chart.data = LineData(data)
        chart.invalidate()
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateGreenStates()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blueColor = ContextCompat.getColor(context!!, R.color.materialBlue)
        yellowColor = ContextCompat.getColor(context!!, R.color.materialYellow)

        val color = ContextCompat.getColor(context!!, R.color.colorPrimary)

        setupChart(tempLineChart, greenColor)
        setupChart(lightLineChart, yellowColor)
        setupChart(hydroLineChart, blueColor)


        //tempLineChart.data = getData(10, 10f, color)
    }

    private fun setupChart(chart: LineChart, color: Int) {

        chart.description.text = ""
        chart.legend.isEnabled = false

        chart.axisRight.isEnabled = false
        chart.axisRight.setDrawLabels(false)

        chart.isDragEnabled = false
        chart.setScaleEnabled(false)

        chart.setTouchEnabled(false)

        chart.axisLeft.valueFormatter = MyValueFormatter()

//        chart.axisLeft.spaceTop = 0F
        chart.axisLeft.spaceBottom = 0F
//        chart.axisLeft.axisMinimum = 0F
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

    inner class MyValueFormatter : ValueFormatter() {

        override fun getFormattedValue(
            value: Float,
            entry: Entry,
            dataSetIndex: Int,
            viewPortHandler: ViewPortHandler
        ): String {
            return String.format("%.2f $",value)
        }
    }
}
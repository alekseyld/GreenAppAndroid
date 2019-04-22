package ru.alekseyld.greenhouseapp.ui.statistics

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
import ru.alekseyld.greenhouseapp.ui.control.DaggerStatisticsComponent
import ru.alekseyld.greenhouseapp.ui.control.StatisticsModule
import ru.alekseyld.greenhouseapp.viewmodel.StatisticsViewModel


class StatisticsFragment : BaseBindingFragment<StatisticsViewModel, FragmentStatisticsBinding>() {

    override fun bindVariable() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val color = ContextCompat.getColor(context!!, R.color.colorPrimary)

        setupChart(tempLineChart, getData(10, 10f, color), Color.rgb(137, 230, 81))
    }

    private fun setupChart(chart: LineChart, data: LineData, color: Int) {

        chart.data = data

        chart.description.text = ""
        chart.legend.isEnabled = false

        chart.axisRight.isEnabled = false

        chart.isDragEnabled = false
        chart.setScaleEnabled(false)

        chart.setTouchEnabled(false)

        chart.axisLeft.valueFormatter = MyValueFormatter()
    }

    private fun getData(count: Int, range: Float, color: Int): LineData {

        val values = ArrayList<Entry>()

        for (i in 0 until count) {
            val `val` = (Math.random() * range).toFloat() + 3
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

        set1.fillColor = color
        set1.setDrawFilled(true)
        set1.fillAlpha = 255
        set1.setDrawCircles(false)

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER

        // create a data object with the data sets
        return LineData(set1)
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
            return String.format("%.2f $",value);
        }
    }
}
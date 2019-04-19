package ru.alekseyld.greenhouseapp.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.ui.control.ControlFragment
import ru.alekseyld.greenhouseapp.ui.settings.SettingsFragment
import ru.alekseyld.greenhouseapp.ui.statistics.StatisticsFragment

class MainActivity(
    private val controlFragment: ControlFragment = ControlFragment(),
    private val settingsFragment: SettingsFragment = SettingsFragment(),
    private val statisticsFragment: StatisticsFragment = StatisticsFragment()

) : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_statistics -> {
                replaceFragment(statisticsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(controlFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                replaceFragment(settingsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.controller_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

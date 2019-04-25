package ru.alekseyld.greenhouseapp.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.api.RetrofitHolder
import javax.inject.Inject


public class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var retrofitHolder: RetrofitHolder

    companion object {
        const val PEFERENCE_NAME = "green_app"

        const val ESP_IP = "esp_ip"
    }

    override fun setPreferenceScreen(preferenceScreen: PreferenceScreen?) {
        super.setPreferenceScreen(preferenceScreen)
        if (preferenceScreen != null) {
            val count = preferenceScreen.preferenceCount
            for (i in 0 until count)
                preferenceScreen.getPreference(i)!!.isIconSpaceReserved = false
        }
    }

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {
        preferenceManager.sharedPreferencesName = PEFERENCE_NAME
        addPreferencesFromResource(R.xml.app_preferences)

        bindPreferenceSummaryToValue(findPreference(ESP_IP))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectDependencies()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun notifyRetrofit(url: String) {
        retrofitHolder.recreateRetrofit(url)
    }

    private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->

        var stringValue = ""

        if (value is SharedPreferences) {
            stringValue = value.getString(preference.key, "")!!
        } else if (value is String){
            stringValue = value
        }

        preference.summary = stringValue

        if (preference.key == ESP_IP && value !is SharedPreferences) {
            notifyRetrofit(stringValue)
        }

        true
    }

    private fun bindPreferenceSummaryToValue(preference: Preference) {
        // Set the listener to watch for value changes.
        preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, preferenceManager.sharedPreferences)
    }

    fun injectDependencies() {
        DaggerSettingsComponent.builder()
            .appComponent(GreenApp.component)
            .build()
            .inject(this)
    }
}
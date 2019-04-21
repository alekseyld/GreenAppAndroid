package ru.alekseyld.greenhouseapp.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import ru.alekseyld.greenhouseapp.R


class SettingsFragment : PreferenceFragmentCompat() {

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

    private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->

        val stringValue = (value as SharedPreferences).getString(preference.key, "")
        preference.summary = stringValue

        true
    }

    private fun bindPreferenceSummaryToValue(preference: Preference) {
        // Set the listener to watch for value changes.
        preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, preferenceManager.sharedPreferences)
    }
}
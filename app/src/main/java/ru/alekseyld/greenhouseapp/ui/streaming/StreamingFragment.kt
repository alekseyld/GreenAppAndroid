package ru.alekseyld.greenhouseapp.ui.streaming

import android.content.SharedPreferences
import kotlinx.android.synthetic.main.fragment_streaming.*
import ru.alekseyld.greenhouseapp.GreenApp
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.base.BaseBindingFragment
import ru.alekseyld.greenhouseapp.databinding.FragmentStreamingBinding
import ru.alekseyld.greenhouseapp.ui.settings.SettingsFragment
import ru.alekseyld.greenhouseapp.viewmodel.StreamingViewModel
import javax.inject.Inject

class StreamingFragment : BaseBindingFragment<StreamingViewModel, FragmentStreamingBinding>() {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onResume() {
        super.onResume()

        webcam1.loadUrl(preferences.getString(SettingsFragment.WEBCAM1_IP, ""))
        webcam2.loadUrl(preferences.getString(SettingsFragment.WEBCAM2_IP, ""))
    }

    override fun onPause() {
        super.onPause()

        webcam1.loadUrl("")
        webcam2.loadUrl("")
    }

    override fun bindVariable() {
        binding.viewModel = viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_streaming

    override fun injectDependencies() {
        DaggerStreamingComponent.builder()
            .appComponent(GreenApp.component)
            .streamingModule(StreamingModule())
            .build()
            .inject(this)
    }

    override fun onBackKeyPressed() {}
}
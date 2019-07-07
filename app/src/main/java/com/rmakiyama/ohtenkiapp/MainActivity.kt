package com.rmakiyama.ohtenkiapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rmakiyama.ohtenkiapp.ui.weather.WeatherFragment
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherFragment.newInstance())
                .commitNow()
        }
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        setFullscreen()
    }

    private fun setFullscreen() {
        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

}

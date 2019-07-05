package com.rmakiyama.ohtenkiapp

import android.os.Bundle
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
    }

}

package com.choota.opencurrency

import android.app.Application
import com.blongho.country_data.World
import com.chibatching.kotpref.Kotpref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenCurrencyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(applicationContext)
        World.init(applicationContext)
    }
}
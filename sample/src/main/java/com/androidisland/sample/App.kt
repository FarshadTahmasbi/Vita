package com.androidisland.sample

import android.app.Application
import com.androidisland.vita.startVita

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startVita()
    }
}
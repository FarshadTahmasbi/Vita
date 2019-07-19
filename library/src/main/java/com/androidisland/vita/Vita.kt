package com.androidisland.vita

import android.app.Application
import android.util.Log

object Vita {

    private var app: Application? = null

    fun init(application: Application) {
        app = application
        app?.registerAppCloseListener(object : AppCloseListener() {
            override fun onAppExit() {
                //TODO clear all app level view models...
                Log.d("test13", "app exit!")
            }
        })
    }

    init {
//        Log.d("test13", "vita init!")
    }
}
package com.androidisland.vita

import android.app.Application
import android.util.Log

internal class Vita internal constructor(app: Application) {

    companion object {
        @Volatile
        private var INSTANCE: Vita? = null

        internal fun getInstance(): Vita {
            return INSTANCE ?: throw IllegalStateException("You should startVita in application onCreate() first")
        }

        internal fun createInstance(app: Application) {
            synchronized(this) {
                INSTANCE = Vita(app)
            }
        }
    }

    init {
        app.registerAppExitListener(object : AppExitListener() {
            override fun onAppExit() {
                this@Vita.onAppExit()
            }
        })
    }

    private fun onAppExit() {
        //TODO handle clearing global viewmodels
        Log.d("test123", "app exit!!!")
    }

    fun x(): Lazy<String> {
        return lazy { "Hi" }
    }

}
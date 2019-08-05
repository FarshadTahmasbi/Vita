package com.androidisland.vita

import android.app.Application
import android.util.Log

/**
 * Responsible for caching ViewModelStores
 */
internal class VitaStore internal constructor(app: Application) {

    companion object {
        @Volatile
        private var INSTANCE: VitaStore? = null

        internal fun getInstance(): VitaStore {
            return INSTANCE ?: throw IllegalStateException("You should startVita in application onCreate() first")
        }

        internal fun createInstance(app: Application) {
            synchronized(this) {
                INSTANCE = VitaStore(app)
            }
        }
    }

    init {
        app.registerAppExitListener(object : AppExitListener() {
            override fun onAppExit() {
                this@VitaStore.onAppExit()
            }
        })
    }

    private fun onAppExit() {
        //TODO handle clearing None viewmodels
        Log.d("test123", "app exit!!!")
    }
}
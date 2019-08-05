package com.androidisland.vita

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

typealias FactoryFun<T> = () -> T

class Vita internal constructor(app: Application) {

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
        //TODO handle clearing None viewmodels
        Log.d("test123", "app exit!!!")
    }

    fun with(owner: VitaOwner) = VitaProvider(owner)
}
package com.androidisland.vita

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

typealias FactoryFun<T> = () -> T

class Vita internal constructor(private val app: Application) {

    private val globalOwner = ViewModelStore()

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
        globalOwner.clear()
    }

    fun with(owner: VitaOwner) = VitaProvider(owner)

    @PublishedApi
    internal fun createGlobalProvider(factory: ViewModelProvider.Factory? = null): ViewModelProvider {
        return if (factory != null) ViewModelProvider(
            globalOwner,
            factory
        )
        else ViewModelProvider(
            globalOwner,
            ViewModelProvider.AndroidViewModelFactory.getInstance(app)
        )
    }
}
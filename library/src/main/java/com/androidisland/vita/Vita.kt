package com.androidisland.vita

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

class Vita internal constructor(private val app: Application) {

    private val globalOwner = ViewModelStore()
    private val viewModelStoreMap = VitaViewModelStoreMap()

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

    /**
     * Creates a VitaProvider to get the view model you want
     * @param owner VitaOwner that is responsible for view model life
     */
    fun with(owner: VitaOwner) = VitaProvider(owner)

    @PublishedApi
    internal fun createGlobalProvider(factory: ViewModelProvider.Factory? = null): ViewModelProvider {
        return ViewModelProvider(
            globalOwner,
            factory ?: ViewModelProvider.AndroidViewModelFactory.getInstance(app)
        )
    }

    /**
     * Responsible for creating provider for view models with multiple owners
     */
    @PublishedApi
    internal inline fun <reified T : ViewModel> createProvider(
        lifecycleOwner: LifecycleOwner,
        factory: ViewModelProvider.Factory? = null
    ): ViewModelProvider {
        //TODO fix this shit
        val store = viewModelStoreMap.getOrCreate<T>(lifecycleOwner)
        return ViewModelProvider(
            store,
            factory ?: ViewModelProvider.AndroidViewModelFactory.getInstance(app)
        )
    }
}
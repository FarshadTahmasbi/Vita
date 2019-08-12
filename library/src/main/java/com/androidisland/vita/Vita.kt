package com.androidisland.vita

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

class Vita internal constructor(@PublishedApi internal val app: Application) {

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


    /**
     * Responsible for creating provider for global view models in application scope
     */
    @PublishedApi
    internal fun createGlobalProvider(factory: ViewModelProvider.Factory? = null): ViewModelProvider {
        return ViewModelProvider(
            globalOwner,
            factory ?: ViewModelProvider.AndroidViewModelFactory.getInstance(app)
        )
    }

    /**
     * Responsible for creating provider for view models with single owner (android default)
     */
    @PublishedApi
    internal fun createSingleProvider(
        lifecycleOwner: LifecycleOwner,
        factory: ViewModelProvider.Factory?
    ): ViewModelProvider {
        return when (lifecycleOwner) {
            is Fragment -> ViewModelProviders.of(lifecycleOwner, factory)
            is FragmentActivity -> ViewModelProviders.of(lifecycleOwner, factory)
            else -> throw IllegalArgumentException("Unsupported owner passed")
        }
    }

    /**
     * Responsible for creating provider for view models with multiple owners
     */
    @PublishedApi
    internal fun <T : ViewModel> createMultipleProvider(
        clazz: Class<T>,
        lifecycleOwner: LifecycleOwner,
        factory: ViewModelProvider.Factory? = null
    ): ViewModelProvider {
        val store = viewModelStoreMap.getOrCreate<T>(clazz, lifecycleOwner)
        return ViewModelProvider(
            store,
            factory ?: ViewModelProvider.AndroidViewModelFactory.getInstance(app)
        )
    }
}
package com.androidisland.vita

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

typealias FactoryFun<T> = () -> T

class VitaBuilder internal constructor(@PublishedApi internal val owner: VitaOwner) {
    /**
     * Use this method to get ViewModel
     * @param factoryFun factory function to pass data to view model by its constructor
     * @return ViewModel object
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : ViewModel> getViewModel(
        noinline factoryFun: FactoryFun<T>? = null
    ): T {
        return when (owner) {
            is VitaOwner.Single -> owner.getViewModel(factoryFun)
            is VitaOwner.Multiple -> owner.getViewModel(factoryFun)
            is VitaOwner.None -> owner.getViewModel(factoryFun)
        }
    }

    /**
     * Same as getViewModel function but returns the result lazily
     */
    inline fun <reified T : ViewModel> viewModel(
        noinline factoryFun: FactoryFun<T>? = null
    ) = lazy {
        getViewModel(factoryFun)
    }

    @Suppress("UNCHECKED_CAST")
    @PublishedApi
    internal inline fun <reified T : ViewModel> VitaOwner.Single.getViewModel(noinline factoryFun: FactoryFun<T>? = null): T {
        val factory = factoryFun?.let {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
            }
        }
        return vita.createSingleProvider(lifecycleOwner, factory)[T::class.java]
    }

    @Suppress("UNCHECKED_CAST")
    @PublishedApi
    internal inline fun <reified T : ViewModel> VitaOwner.Multiple.getViewModel(noinline factoryFun: FactoryFun<T>? = null): T {
        val factory = factoryFun?.let {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
            }
        }
        return vita.createMultipleProvider(T::class.java, lifecycleOwner, factory)[T::class.java]
    }

    @Suppress("UNCHECKED_CAST")
    @PublishedApi
    internal inline fun <reified T : ViewModel> VitaOwner.None.getViewModel(noinline factoryFun: FactoryFun<T>? = null): T {
        val factory = factoryFun?.let {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
            }
        }
        return vita.createGlobalProvider(factory)[T::class.java]
    }
}
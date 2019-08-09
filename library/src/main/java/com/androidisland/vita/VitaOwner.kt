package com.androidisland.vita

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

sealed class VitaOwner {
    /**
     * Use this when your view model has only one owner,
     * provided view models are alive until the owner is alive
     */
    class Single(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner(), LifecycleOwner by lifecycleOwner {
        @Suppress("UNCHECKED_CAST")
        @PublishedApi internal inline fun <reified T : ViewModel> getViewModel(noinline factoryFun: FactoryFun<T>? = null): T {
            val factory = factoryFun?.let {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
                }
            }
            return when (lifecycleOwner) {
                is Fragment -> ViewModelProviders.of(lifecycleOwner, factory)[T::class.java]
                is FragmentActivity -> ViewModelProviders.of(lifecycleOwner, factory)[T::class.java]
                else -> throw IllegalArgumentException("Unsupported owner passed")
            }
        }
    }

    /**
     * Use this when you want to share a view model between Multiple life cycle owners,
     * it means you can share it between activities or fragment with different host activity ans so on...
     * provided view models will be cleared when the last owner is destroyed
     */
    class Multiple(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner(), LifecycleOwner by lifecycleOwner

    /**
     * In case you need a global view model that is available all over the app,
     * provided view model will be cleared when app is closed (last activity is finished)
     */
    object None : VitaOwner()
}
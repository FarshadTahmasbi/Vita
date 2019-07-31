package com.androidisland.vita

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

typealias FactoryFun<T> = () -> T

/**
 * This class acts as an interface between Vita singleton and LifeCycleOwner, and it
 * prevents conflicts with extension functions like those in Koin library
 */
class VitaProvider internal constructor(
    protected inline val vita: Vita,
    protected inline val caller: LifecycleOwner
) {

    /**
     * Use this method to get ViewModel, you can control the life of ViewModel by the owner you pass,
     * If you pass a Fragment or FragmentActivity as owner, the ViewModel is alive while owner is alive
     * (This is exactly same as it was before!)
     * But if you pass a ProcessLifecycleOwner, ViewModel will be created in app level and stays alive unless
     * the last owner is dead, this is useful when you want to share ViewModels between activities
     * @param LifeCycleOwner object for ViewModel, it can be a Fragment, FragmentActivity or ProcessLifecycleOwner
     * @return ViewModel object
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : ViewModel> getViewModel(
        owner: LifecycleOwner,
        noinline factoryFun: FactoryFun<T>? = null
    ): T {
        val factory = factoryFun?.let {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
            }
        }
        return when (owner) {
            is Fragment -> ViewModelProviders.of(owner, factory)[T::class.java]
            is FragmentActivity -> ViewModelProviders.of(owner, factory)[T::class.java]
            is ProcessLifecycleOwner -> TODO("not implemented")
            else -> throw IllegalArgumentException("Unsupported owner passed")
        }
    }

    inline fun <reified T : ViewModel> viewModel(
        owner: LifecycleOwner,
        noinline factoryFun: FactoryFun<T>? = null
    ) = lazy {
        getViewModel(owner, factoryFun)
    }
}
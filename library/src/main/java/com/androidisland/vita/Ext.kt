package com.androidisland.vita

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

internal fun Application.registerAppExitListener(listener: AppExitListener) {
    registerComponentCallbacks(listener)
    registerActivityLifecycleCallbacks(listener)
}

internal fun Application.unregisterAppExitListener(listener: AppExitListener) {
    unregisterComponentCallbacks(listener)
    unregisterActivityLifecycleCallbacks(listener)
}

/**
 * Starts vita and make it ready to use
 */
fun Application.startVita() {
    Vita.createInstance(this)
}

/**
 * vita singleton, all starts from here!
 */
val Any.vita: Vita
    get() = Vita.getInstance()

/**
 * Determines if lifecycle owner is about to change config or not
 */
internal fun LifecycleOwner.isChangingConfigurations(): Boolean {
    return when (this) {
        is Fragment -> activity != null && activity?.isChangingConfigurations ?: false
        is FragmentActivity -> isChangingConfigurations
        else -> false
    }
}

/**
 * Get class name to use as key
 */
inline fun <reified T : Any> Any.className(): String {
    return T::class.java.name
}

/**
 * Log for debug purpose
 */
internal fun Any.logD(msg: String) {
    if (BuildConfig.DEBUG)
        Log.d("Vita", msg)
}

inline fun <reified T : ViewModel> Vita.sinleOwner(
    lifecycleOwner: LifecycleOwner,
    noinline factoryFun: FactoryFun<T>? = null
) = with(VitaOwner.Single(lifecycleOwner)).getViewModel(factoryFun)

inline fun <reified T : ViewModel> Vita.sinleOwnerLazy(
    lifecycleOwner: LifecycleOwner,
    noinline factoryFun: FactoryFun<T>? = null
) = lazy {
    with(VitaOwner.Single(lifecycleOwner)).getViewModel(factoryFun)
}

inline fun <reified T : ViewModel> Vita.multipleOwner(
    lifecycleOwner: LifecycleOwner,
    noinline factoryFun: FactoryFun<T>? = null
) = with(VitaOwner.Multiple(lifecycleOwner)).getViewModel(factoryFun)

inline fun <reified T : ViewModel> Vita.multipleOwnerLazy(
    lifecycleOwner: LifecycleOwner,
    noinline factoryFun: FactoryFun<T>? = null
) = lazy {
    with(VitaOwner.Multiple(lifecycleOwner)).getViewModel(factoryFun)
}


inline fun <reified T : ViewModel> Vita.noneOwner(
    noinline factoryFun: FactoryFun<T>? = null
) = with(VitaOwner.None).getViewModel(factoryFun)

inline fun <reified T : ViewModel> Vita.noneOwnerLazy(
    noinline factoryFun: FactoryFun<T>? = null
) = lazy {
    with(VitaOwner.None).getViewModel(factoryFun)
}
package com.androidisland.vita

import android.app.Application
import androidx.fragment.app.FragmentActivity

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
 * vita singleton, helps to get the desired view model
 */
val Any.vita: Vita
    get() = Vita.getInstance()

fun FragmentActivity.addVitaLifeCycleObserver(observer: VitaDestroyObserver) {
    lifecycle.addObserver(observer)
}
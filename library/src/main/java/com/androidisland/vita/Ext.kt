package com.androidisland.vita

import android.app.Application

internal fun Application.registerAppExitListener(listener: AppExitListener) {
    registerComponentCallbacks(listener)
    registerActivityLifecycleCallbacks(listener)
}

internal fun Application.unregisterAppExitListener(listener: AppExitListener) {
    unregisterComponentCallbacks(listener)
    unregisterActivityLifecycleCallbacks(listener)
}

/**
 * Starts Vita and make it ready to use
 */
fun Application.startVita() {
    com.androidisland.vita.Vita.createInstance(this)
}

/**
 * Vita singleton, helps to get the desired view model
 */
val Any.Vita: Vita
    get() = com.androidisland.vita.Vita.getInstance()
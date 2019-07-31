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
    Vita.createInstance(this)
}
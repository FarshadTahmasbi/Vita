package com.androidisland.vita

import android.app.Application
import androidx.lifecycle.LifecycleOwner

internal fun Application.registerAppExitListener(listener: AppExitListener) {
    registerComponentCallbacks(listener)
    registerActivityLifecycleCallbacks(listener)
}

internal fun Application.unregisterAppExitListener(listener: AppExitListener) {
    unregisterComponentCallbacks(listener)
    unregisterActivityLifecycleCallbacks(listener)
}

fun Application.startVita() {
    Vita.createInstance(this)
}

val LifecycleOwner.vita: Vita
    get() = Vita.getInstance()
package com.androidisland.vita

import android.app.Application

internal fun Application.registerAppCloseListener(listener: AppCloseListener) {
    registerComponentCallbacks(listener)
    registerActivityLifecycleCallbacks(listener)
}

internal fun Application.unregisterAppCloseListener(listener: AppCloseListener) {
    unregisterComponentCallbacks(listener)
    unregisterActivityLifecycleCallbacks(listener)
}
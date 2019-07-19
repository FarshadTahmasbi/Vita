package com.androidisland.vita

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle

internal abstract class AppCloseListener :
    Application.ActivityLifecycleCallbacks, ComponentCallbacks {

    private var configChanged = false
    private var activityCount = 0
        set(value) {
            field = value
            if (field == 0) onAppExit()
        }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        if (configChanged) {
            configChanged = false
        } else {
            activityCount++
        }
    }

    override fun onActivityDestroyed(p0: Activity) {
        if (!configChanged)
            activityCount--
    }

    override fun onLowMemory() {
    }

    override fun onConfigurationChanged(p0: Configuration) {
        configChanged = true
    }

    abstract fun onAppExit()
}
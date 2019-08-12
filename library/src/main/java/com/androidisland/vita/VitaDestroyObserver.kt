package com.androidisland.vita

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class VitaDestroyObserver(private val activity: FragmentActivity) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!activity.isChangingConfigurations)
            onLifeCycleDestroy()
    }

    /**
     * This fun will be called if lifecycle owner is destroyed without config changes
     */
    abstract fun onLifeCycleDestroy()
}
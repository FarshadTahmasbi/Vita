package com.androidisland.vita

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

@PublishedApi internal abstract class VitaDestroyObserver(private val lifecycleOwner: LifecycleOwner) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!lifecycleOwner.isChangingConfigurations())
            onLifeCycleDestroy()
    }

    /**
     * This fun will be called if lifecycle owner is destroyed without config changes
     */
    abstract fun onLifeCycleDestroy()
}
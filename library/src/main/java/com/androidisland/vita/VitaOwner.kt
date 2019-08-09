package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner

sealed class VitaOwner {
    /**
     * Use this when your view model has only one owner,
     * provided view models are alive until the owner is alive
     */
    class Single(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner()

    /**
     * Use this when you want to share a view model between Multiple life cycle owners,
     * it means you can share it between activities or fragment with different host activity ans so on...
     * provided view models will be cleared when the last owner is destroyed
     */
    class Multiple(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner()

    /**
     * In case you need a global view model that is available all over the app,
     * provided view model will be cleared when app is closed (last activity is finished)
     */
    object None : VitaOwner()
}
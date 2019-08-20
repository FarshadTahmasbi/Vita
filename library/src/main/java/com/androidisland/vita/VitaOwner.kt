package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner

sealed class VitaOwner {
    /**
     * Use this when you need a ViewModel with only one owner (default behavior),
     * provided ViewModels are alive while the owner is alive
     * @param lifecycleOwner owner of the ViewModel
     */
    class Single(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner()

    /**
     * Use this when you want to share a ViewModel between multiple lifecycle owners,
     * it means you can share it between activities or fragments with different host activity and so on...
     * provided view models will be cleared when the last owner is destroyed
     * @param lifecycleOwner owner of the ViewModel
     */
    class Multiple(@PublishedApi internal val lifecycleOwner: LifecycleOwner) : VitaOwner()

    /**
     * In case you need a global ViewModel that is available in application scope,
     * provided ViewModel will be cleared when app is closed (last activity is finished)
     */
    object None : VitaOwner()
}
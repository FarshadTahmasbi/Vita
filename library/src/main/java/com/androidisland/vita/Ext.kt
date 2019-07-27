package com.androidisland.vita

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

internal fun Application.registerAppCloseListener(listener: AppCloseListener) {
    registerComponentCallbacks(listener)
    registerActivityLifecycleCallbacks(listener)
}

internal fun Application.unregisterAppCloseListener(listener: AppCloseListener) {
    unregisterComponentCallbacks(listener)
    unregisterActivityLifecycleCallbacks(listener)
}

inline fun <reified T : ViewModel> LifecycleOwner.getViewModel(owner : LifecycleOwner?) : T {
    //1. owner is fragment, same as usual
    //2. owner is activity, same as usual
    //3. owner is process, create store, add observer on owners, and handle clear
    //4. no owner(null), clear on app exit
    //*** add factory support!
    //TODO
}
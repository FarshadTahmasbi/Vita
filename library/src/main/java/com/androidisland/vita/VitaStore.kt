package com.androidisland.vita

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import java.util.HashSet

internal class VitaStore<T : ViewModel>(callbacks: Callback? = null) : LifecycleObserver {

    private val store = ViewModelStore()
    private val clients = HashSet<Lifecycle>()



    interface Callback {
        fun onLastClientsDestroyed()
    }
}
package com.example.sample.lib

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelStore

class ViewModelStoreWrapper : LifecycleObserver{
    val viewModelStore = ViewModelStore()
    fun observe(lifecycle : Lifecycle){
    }
}
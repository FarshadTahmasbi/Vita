package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

internal class VitaViewModelStoreMap {
    //Key -> ViewModel class name
    //Value -> ViewModelStore
    private val map = HashMap<String, VitaStore>()

    inline fun <reified T : ViewModel> getStore(): VitaStore? = map[T::class.java.name]

    inline fun <reified T : ViewModel> createStore(owner: LifecycleOwner): VitaStore{

    }
}
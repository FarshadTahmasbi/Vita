package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

internal class VitaViewModelStoreMap : VitaStore.Callback {

    //Key -> ViewModel class name
    //Value -> ViewModelStore
    private val storeMap = HashMap<String, VitaStore>()

    private inline fun <reified T : ViewModel> get(): VitaStore? = storeMap[T::class.java.name]

    private inline fun <reified T : ViewModel> create(owner: LifecycleOwner): VitaStore {
        return VitaStore.create<T>(owner, this).apply {
            storeMap[T::class.java.name] = this
        }
    }

    @PublishedApi internal inline fun <reified T : ViewModel> getOrCreate(owner: LifecycleOwner): VitaStore =
        get<T>() ?: create<T>(owner)

    override fun onStoreClear(clazz: Class<*>) {
        storeMap.remove(clazz.name)
    }
}
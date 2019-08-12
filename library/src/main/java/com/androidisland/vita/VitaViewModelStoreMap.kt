package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

internal class VitaViewModelStoreMap : VitaStore.Callback {

    //Key -> ViewModel class name
    //Value -> ViewModelStore
    private val storeMap = HashMap<String, VitaStore>()

    private fun <T : ViewModel> get(clazz: Class<T>): VitaStore? = storeMap[clazz.name]

    private fun <T : ViewModel> create(
        clazz: Class<T>,
        owner: LifecycleOwner
    ): VitaStore {
        return VitaStore.create<T>(clazz, owner, this).apply {
            storeMap[clazz.name] = this
        }
    }

    fun <T : ViewModel> getOrCreate(
        clazz: Class<T>,
        owner: LifecycleOwner
    ): VitaStore =
        get(clazz) ?: create(clazz, owner)

    override fun onStoreClear(clazz: Class<*>) {
        storeMap.remove(clazz.name)
    }
}
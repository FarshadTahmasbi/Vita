package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

/**
 * This class is just a wrapper and a map and is responsible to create a VitaSharedStore and
 * manage them so we can share ViewModels between LifeCycleOwners
 */
internal class VitaSharedStoreFactory : VitaSharedStore.Callback {

    //Key -> ViewModel class name
    //Value -> ViewModelStore
    private val storeMap = HashMap<String, VitaSharedStore>()

    private fun <T : ViewModel> get(
        clazz: Class<T>,
        owner: LifecycleOwner
    ): VitaSharedStore? = storeMap[clazz.name]?.apply {
        addOwner(owner)
    }

    private fun <T : ViewModel> create(
        clazz: Class<T>,
        owner: LifecycleOwner
    ): VitaSharedStore {
        return VitaSharedStore.create(clazz, this).apply {
            addOwner(owner)
            storeMap[clazz.name] = this
        }
    }

    fun <T : ViewModel> getOrCreate(
        clazz: Class<T>,
        owner: LifecycleOwner
    ): VitaSharedStore = get(clazz, owner) ?: create(clazz, owner)


    override fun onStoreClear(clazz: Class<*>) {
        storeMap.remove(clazz.name)
    }
}
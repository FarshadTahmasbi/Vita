package com.androidisland.vita

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import java.util.*

/**
 * This is a ViewModelStore that helps to share ViewModels between LifeCycleOwners
 * Also it tracks lifecycle of owners so when last registered owner for a specific ViewModel
 * dies, it clears the store and ViewModel as well...
 */
internal class VitaSharedStore private constructor(
    private val clazz: Class<*>,
    private val callback: Callback
) : ViewModelStore(), LifecycleObserver {

    private val owners = HashSet<String>()

    companion object {
        internal fun <T : ViewModel> create(
            clazz: Class<T>,
            callback: Callback
        ): VitaSharedStore = VitaSharedStore(clazz, callback)

    }

    private fun createDestroyObserver(owner: LifecycleOwner) =
        object : VitaDestroyObserver(owner) {
            override fun onLifeCycleDestroy() {
                removeOwner(owner)
            }
        }

    private fun getOwnerKey(owner : LifecycleOwner) = owner.toString()

    fun addOwner(owner: LifecycleOwner) {
        if (owners.contains(getOwnerKey(owner))){
            logD("$owner is already added")
            return
        }
        owners.add(getOwnerKey(owner))
        owner.apply {
            lifecycle.addObserver(createDestroyObserver(this))
        }
        logD("$owner registered in $this as owner of ${clazz.simpleName} ")
    }

    private fun removeOwner(owner: LifecycleOwner) {
        owners.remove(getOwnerKey(owner))
        logD("$owner unregistered from $this as ${clazz.simpleName}'s owner")
        if (owners.isEmpty()) {
            //Clear store when last owner is dead
            clear()
            callback.onStoreClear(clazz)
            logD("$this, store of ${clazz.simpleName} cleared")
        }
    }

    interface Callback {
        fun onStoreClear(clazz: Class<*>)
    }
}
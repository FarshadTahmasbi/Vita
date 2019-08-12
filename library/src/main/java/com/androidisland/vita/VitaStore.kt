package com.androidisland.vita

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import java.util.*

class VitaStore private constructor(
    private val clazz: Class<*>,
    private val callback: Callback
) : ViewModelStore(), LifecycleObserver {

    private val ownersName = HashSet<String>()

    companion object {
        internal inline fun <reified T : ViewModel> create(callback: Callback): VitaStore {
            return VitaStore(T::class.java, callback)
        }
    }

    private fun createDestroyObserver(owner: LifecycleOwner) =
        object : VitaDestroyObserver(owner) {
            override fun onLifeCycleDestroy() {
                removeOwner(owner)
            }
        }

    fun addOwner(owner: LifecycleOwner) {
        ownersName.add(owner::class.java.name)
        owner.apply {
            lifecycle.addObserver(createDestroyObserver(this))
        }
    }

    private fun removeOwner(owner: LifecycleOwner) {
        ownersName.remove(owner::class.java.name)
        if (ownersName.isEmpty()) {
            //Clear store when last owner is dead
            clear()
            callback.OnStoreClear(clazz)
        }
    }

    interface Callback {
        fun OnStoreClear(clazz: Class<*>)
    }
}
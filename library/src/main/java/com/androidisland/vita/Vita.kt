package com.androidisland.vita

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.lang.RuntimeException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Vita internal constructor(app: Application) {

    companion object {
        @Volatile
        private var INSTANCE: Vita? = null

        internal fun getInstance(): Vita {
            return INSTANCE ?: throw IllegalStateException("You should startVita in application onCreate() first")
        }

        internal fun createInstance(app: Application) {
            INSTANCE = Vita(app)
        }
    }

    init {
        app.registerAppExitListener(object : AppExitListener() {
            override fun onAppExit() {
                this@Vita.onAppExit()
            }
        })
    }

    private fun onAppExit() {
        //TODO handle clearing global viewmodels
        Log.d("test123", "app exit!!!")
    }


    /**
     * Use this method to get ViewModel, you can control the life of ViewModel by the owner you pass,
     * If you pass a Fragment or FragmentActivity as owner, the ViewModel is alive while owner is alive
     * (This is exactly same as it was before!)
     * But if you pass a ProcessLifecycleOwner, ViewModel will be created in app level and stays alive unless
     * the last owner is dead, this is useful when you want to share ViewModels between activities
     * @param LifeCycleOwner object for ViewModel, it can be a Fragment, FragmentActivity or ProcessLifecycleOwner
     * @return ViewModel object
     */
    inline fun <reified T : ViewModel> getViewModel(owner: LifecycleOwner): T {
        return when (owner) {
            is Fragment -> ViewModelProviders.of(owner)[T::class.java]
            is FragmentActivity -> ViewModelProviders.of(owner)[T::class.java]
            is ProcessLifecycleOwner -> TODO("not implemented")
            else -> throw IllegalArgumentException("Unsupported owner passed")
        }
    }

    //TODO overload getViewModel with factory!

    inline fun <reified T : ViewModel> getViewModel(): T {
        TODO("not implemented")
    }

    //TODO lazy version for getViewModel

//    inline fun <reified T : ViewModel> getViewModel(owner : LifecycleOwner?) : T {
//        //1. owner is fragment, same as usual
//        //2. owner is activity, same as usual
//        //3. owner is process, create store, add observer on owners, and handle clear
//        //4. no owner(null), clear on app exit
//        //*** add factory support!
//        //TODO
//    }

    fun x(): Lazy<String> {
        return lazy { "Hi" }
    }

}
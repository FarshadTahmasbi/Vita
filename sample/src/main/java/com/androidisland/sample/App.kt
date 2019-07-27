package com.androidisland.sample

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.androidisland.vita.Vita

class App : Application(), LifecycleObserver {

    val store = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        val lc = ProcessLifecycleOwner.get()
        Log.d("test1", "-->${lc::class.java}")
        Vita.init(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
        Log.d("test1", "in foreground!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        Log.d("test1", "in background!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        store.clear()
        Log.d("test1", "destroyed!")
    }

    fun getVita():VitaVM {
        val provider = ViewModelProvider(
            store,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        )
       return provider[VitaVM::class.java]
    }
}
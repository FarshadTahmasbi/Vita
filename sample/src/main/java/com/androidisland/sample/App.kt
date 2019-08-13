package com.androidisland.sample

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.androidisland.vita.startVita
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        val lc = ProcessLifecycleOwner.get()
        Log.d("test1", "-->${lc::class.java}")

        val module = module {
            viewModel {
                VitaVM("Ok")
            }
        }

        startKoin {
            androidContext(this@App)
            modules(module)
        }
        startVita()
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
        Log.d("test1", "destroyed!")
    }
}
package com.androidisland.vita

import android.app.Application
import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object Vita {

    private var app: Application? = null

    fun init(application: Application) {
        app = application
        app?.registerAppCloseListener(object : AppCloseListener() {
            override fun onAppExit() {
                //TODO clear all app level view models...
                Log.d("test13", "app exit!")
            }
        })
    }

    inline fun <reified T> getViewModel(): T {
    }

    fun viewModel(): ReadWriteProperty<Any?, Int> {
        return object : ReadWriteProperty<Any?, Int> {
            override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
                return 1000
            }

            override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            }
        }
    }

    fun x(): Lazy<String> {
        return lazy { "Hi" }
    }

    init {
//        Log.d("test13", "vita init!")
    }
}
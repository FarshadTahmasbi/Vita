package com.androidisland.vita

import android.app.Application
import android.content.Context
import android.util.Log
import kotlin.IllegalArgumentException
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
        Log.d("test123", "app exit!!!")
    }

//    inline fun <reified T : ViewModel> getViewModel(owner : LifecycleOwner?) : T {
//        //1. owner is fragment, same as usual
//        //2. owner is activity, same as usual
//        //3. owner is process, create store, add observer on owners, and handle clear
//        //4. no owner(null), clear on app exit
//        //*** add factory support!
//        //TODO
//    }

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
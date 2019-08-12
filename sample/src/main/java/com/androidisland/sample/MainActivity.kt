package com.androidisland.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidisland.sample.viewmodel.ViewModelNoFactory
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class MainActivity : AppCompatActivity() {

    val vm by vita.with(VitaOwner.Single(this)).viewModel() {
        VitaVM("lazy vita!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerCallback()

        val store = (application as App).store
        val provider = ViewModelProvider(
            store,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
//        val vm = provider[VitaVM::class.java]
//        Log.d("test1", "store-> $store")
//        Log.d("test1", "provider-> $provider")
//        Log.d("test1", "vm-> $vm")
//        vm.live.observe(this, Observer {
//            text.text = it
//            Log.d("test1", "live-> $it")
//        })
//        vm.setData("Hello world!")

        val vm2 = vita
            .with(VitaOwner.None)
            .getViewModel() {
                VitaVM("vita global!")
            }

        val vm3 = vita
            .with(VitaOwner.None)
            .getViewModel<ViewModelNoFactory>()

        val vm4 = vita
            .with(VitaOwner.None)
            .getViewModel<ViewModelNoFactory>()

        Log.d("test123", "vM2=>$vm2")
        Log.d("test123", "vM3=>$vm3")
        Log.d("test123", "vM4=>$vm4")
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).store.clear()
    }

    private fun registerCallback() {
//        lifecycle.addObserver(object : LifecycleObserver {
//            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//            fun onCreate() {
//                Log.d("test130", "created...")
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//            fun onDestroy() {
//                Log.d("test130", "destroyed...")
//            }
//        })
    }
}

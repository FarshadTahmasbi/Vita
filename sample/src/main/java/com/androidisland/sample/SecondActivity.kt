package com.androidisland.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val store = (application as App).store
        val provider = ViewModelProvider(
            store,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
        val vm = provider[VitaVM::class.java]
        Log.d("test2", "store-> $store")
        Log.d("test2", "provider-> $provider")
        Log.d("test2", "vm-> $vm")
        vm.live.observe(this, Observer {
            Log.d("test2", "live-> $it")
            edit_text.setText(it)
        })

        btn.setOnClickListener {
            vm.setData(edit_text.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).store.clear()
    }
}

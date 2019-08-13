package com.androidisland.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.viewmodel.ViewModelNoFactory
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val vm by vita.with(VitaOwner.Multiple(this)).viewModel<ViewModelNoFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm2 = vita
            .with(VitaOwner.None)
            .getViewModel() {
                VitaVM("vita global!")
            }
        create_btn.setOnClickListener {
            val vm = vita.with(VitaOwner.Multiple(this))
                .getViewModel<ViewModelNoFactory>()
            Log.d("Vita", "MainActivity#vm=>$vm")
        }
    }
}

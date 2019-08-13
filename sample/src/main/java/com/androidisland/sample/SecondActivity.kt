package com.androidisland.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidisland.sample.viewmodel.ViewModelNoFactory
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    val vm by vita.with(VitaOwner.Multiple(this)).viewModel<ViewModelNoFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        btn.setOnClickListener {
//            vm.setData(edit_text.text.toString())
//        }
        Log.d("Vita", "SecondActivity#vm=>$vm")
    }
}

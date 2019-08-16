package com.androidisland.sample.view.activity.no_owner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidisland.sample.R
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class NoOwnerActivity : AppCompatActivity() {

    private val viewModel by lazy {
        vita.with(VitaOwner.None).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_owner)
    }
}

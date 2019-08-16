package com.androidisland.sample.view.activity.multiple_owners

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_no_frag.*

class NoFragActivity : AppCompatActivity() {

    private val viewModel by vita.with(VitaOwner.Multiple(this)).viewModel<ColorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_frag)
        viewModel.observeColor(this, Observer {
            main_group.setBackgroundColor(it)
        })
        main_group.setOnClickListener {
            openColorDialog("NoFragActivity"){
                changeColor(it)
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)

}

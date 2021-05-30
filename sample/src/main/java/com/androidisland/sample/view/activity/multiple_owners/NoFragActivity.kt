package com.androidisland.sample.view.activity.multiple_owners

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.androidisland.sample.Constants
import com.androidisland.sample.databinding.ActivityNoFragBinding
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.view.activity.ViewBindingActivity
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class NoFragActivity : ViewBindingActivity<ActivityNoFragBinding>() {

    private val viewModel by lazy {
        vita.with(VitaOwner.Multiple(this)).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            viewModel.observeColor(this@NoFragActivity, Observer {
                Log.d(Constants.TAG, "NoFragActivity, Color changed")
                mainGroup.setBackgroundColor(it)
            })
            mainGroup.setOnClickListener {
                openColorDialog("NoFragActivity") {
                    changeColor(it)
                }
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
    override fun provideViewBinding(inflater: LayoutInflater) =
        ActivityNoFragBinding.inflate(inflater)

}

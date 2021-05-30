package com.androidisland.sample.view.activity.multiple_owners

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.androidisland.sample.databinding.ActivityMultipleOwnerBinding
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.view.activity.ViewBindingActivity
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class MultipleOwnerActivity : ViewBindingActivity<ActivityMultipleOwnerBinding>() {

    private val viewModel by lazy {
        vita.with(VitaOwner.Multiple(this)).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            mainGroup.setOnClickListener {
                openColorDialog("MultipleOwnerActivity") { changeColor(it) }
            }
            secondActivityBtn.setOnClickListener {
                Intent(
                    this@MultipleOwnerActivity,
                    NoFragActivity::class.java
                ).apply { startActivity(this) }
            }
            newInstanceBtn.setOnClickListener {
                Intent(
                    this@MultipleOwnerActivity,
                    MultipleOwnerActivity::class.java
                ).apply { startActivity(this) }
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
    override fun provideViewBinding(inflater: LayoutInflater) =
        ActivityMultipleOwnerBinding.inflate(inflater)
}

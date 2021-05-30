package com.androidisland.sample.view.activity.single_owner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.databinding.ActivitySingleOwnerBinding
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.view.activity.ViewBindingActivity
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class SingleOwnerActivity : ViewBindingActivity<ActivitySingleOwnerBinding>() {

    private val viewModel by lazy {
        vita.with(VitaOwner.Single(this)).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            mainGroup.setOnClickListener {
                openColorDialog("SingleOwnerActivity") { changeColor(it) }
            }
            newInstanceBtn.setOnClickListener {
                Intent(
                    this@SingleOwnerActivity,
                    SingleOwnerActivity::class.java
                ).apply { startActivity(this) }
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
    override fun provideViewBinding(inflater: LayoutInflater) =
        ActivitySingleOwnerBinding.inflate(inflater)

}

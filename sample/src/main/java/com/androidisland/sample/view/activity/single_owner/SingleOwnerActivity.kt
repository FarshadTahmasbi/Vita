package com.androidisland.sample.view.activity.single_owner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_single_owner.*

class SingleOwnerActivity : AppCompatActivity() {

    private val viewModel by lazy {
        vita.with(VitaOwner.Single(this)).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_owner)
        main_group.setOnClickListener {
            openColorDialog("SingleOwnerActivity") { changeColor(it) }
        }
        new_instance_btn.setOnClickListener {
            Intent(this, SingleOwnerActivity::class.java).apply { startActivity(this) }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)

}

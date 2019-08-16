package com.androidisland.sample.view.activity.multiple_owners

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_two_frags.*

class TwoFragsActivity : AppCompatActivity() {

    private val viewModel by vita.with(VitaOwner.Multiple(this)).viewModel<ColorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_frags)
        main_group.setOnClickListener {
            openColorDialog("TwoFragsActivity") { changeColor(it) }
        }
        second_activity_btn.setOnClickListener {
            Intent(this, NoFragActivity::class.java).apply { startActivity(this) }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
}

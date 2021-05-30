package com.androidisland.sample.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.databinding.ActivityMainBinding
import com.androidisland.sample.view.activity.multiple_owners.MultipleOwnerActivity
import com.androidisland.sample.view.activity.no_owner.NoOwnerActivity
import com.androidisland.sample.view.activity.single_owner.SingleOwnerActivity

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            singleOwnerBtn.setOnClickListener {
                Intent(this@MainActivity, SingleOwnerActivity::class.java)
                    .apply { startActivity(this) }
            }
            multipleOwnerBtn.setOnClickListener {
                Intent(this@MainActivity, MultipleOwnerActivity::class.java)
                    .apply { startActivity(this) }
            }
            noOwnerBtn.setOnClickListener {
                Intent(this@MainActivity, NoOwnerActivity::class.java)
                    .apply { startActivity(this) }
            }
        }
    }

    override fun provideViewBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(inflater)
}

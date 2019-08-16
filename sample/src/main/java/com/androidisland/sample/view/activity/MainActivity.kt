package com.androidisland.sample.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.view.activity.multiple_owners.TwoFragsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        single_owner_btn.setOnClickListener {

        }
        multiple_owner_btn.setOnClickListener {
            Intent(this, TwoFragsActivity::class.java)
                .apply { startActivity(this) }
        }
        no_owner_btn.setOnClickListener {

        }
    }
}

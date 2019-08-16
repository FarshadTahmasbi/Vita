package com.androidisland.sample.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidisland.sample.R
import com.androidisland.sample.view.activity.multiple_owners.MultipleOwnerActivity
import com.androidisland.sample.view.activity.no_owner.NoOwnerActivity
import com.androidisland.sample.view.activity.single_owner.SingleOwnerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        single_owner_btn.setOnClickListener {
            Intent(this, SingleOwnerActivity::class.java)
                .apply { startActivity(this) }
        }
        multiple_owner_btn.setOnClickListener {
            Intent(this, MultipleOwnerActivity::class.java)
                .apply { startActivity(this) }
        }
        no_owner_btn.setOnClickListener {
            Intent(this, NoOwnerActivity::class.java)
                .apply { startActivity(this) }
        }
    }
}

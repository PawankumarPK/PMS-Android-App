package com.purpleshade.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.purpleshade.pms.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
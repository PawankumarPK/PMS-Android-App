package com.purpleshade.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.purpleshade.pms.R

class BaseActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        toolbar = findViewById<Toolbar>(R.id.mToolbar)


    }
}
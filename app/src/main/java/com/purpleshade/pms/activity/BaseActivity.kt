package com.purpleshade.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.purpleshade.pms.R

class BaseActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var fragmentTitle : TextView
    lateinit var backButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        toolbar = findViewById<Toolbar>(R.id.mToolbar)
        fragmentTitle = findViewById<TextView>(R.id.mFragmentTitle)
        backButton = findViewById<ImageView>(R.id.mBack)



    }
}
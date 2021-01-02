package com.purpleshade.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.purpleshade.pms.R
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.Helper

class BaseActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var fragmentTitle : TextView
    lateinit var backButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        RetrofitClient.initRosAPI(Helper.getConfigValue(this, "api_url")!!)

        toolbar = findViewById<Toolbar>(R.id.mToolbar)
        fragmentTitle = findViewById<TextView>(R.id.mFragmentTitle)
        backButton = findViewById<ImageView>(R.id.mBackButton)




    }
}
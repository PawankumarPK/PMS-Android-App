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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        RetrofitClient.initRosAPI(Helper.getConfigValue(this, "api_url")!!)

    }
}
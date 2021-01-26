package com.purpleshade.pms.activity

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.purpleshade.pms.R
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.Helper
import com.purpleshade.pms.utils.NoInternetException
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        RetrofitClient.initRosAPI(Helper.getConfigValue(this, "api_url")!!)
        checkConnection()
    }

     private fun checkConnection() {
        val manager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo
        if (activeNetwork == null) {
            mNetworkCheckLayout.show()
        }else{
            mNetworkCheckLayout.gone()
        }
         Handler().postDelayed({
             checkConnection()
         },500)
    }


}
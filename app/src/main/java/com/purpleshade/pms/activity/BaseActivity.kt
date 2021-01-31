package com.purpleshade.pms.activity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.purpleshade.pms.R
import com.purpleshade.pms.db.MyDatabase
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.*
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    companion object {
        var INSTANCE: MyDatabase? = null
    }
    val user = RoomUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        RetrofitClient.initRosAPI(Helper.getConfigValue(this, "api_url")!!)
        INSTANCE = getAppDataBase(applicationContext)!!

        /*val listData = INSTANCE!!.myDao().user
        if (listData.isEmpty())
            toast("Empty DB")
        else
            toast("User Added")
        */
        checkConnection()

    }

    private fun checkConnection() {
        val manager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo
        if (activeNetwork == null) {
            mNetworkCheckLayout.show()
            mProgressBar.hide()
        } else {
            mNetworkCheckLayout.gone()
        }
        Handler().postDelayed({
            checkConnection()
        }, 500)
    }


    fun getAppDataBase(context: Context): MyDatabase? {
        if (INSTANCE == null) {
            synchronized(MyDatabase::class) {
                INSTANCE = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "myDB")
                    .allowMainThreadQueries().build()
            }
        }
        return INSTANCE
    }


}